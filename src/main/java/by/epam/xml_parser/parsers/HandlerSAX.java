package by.epam.xml_parser.parsers;

import by.epam.xml_parser.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class HandlerSAX extends DefaultHandler {
    private static Logger logger = LogManager.getLogger();
    private String thisElement;
    private Tariff.Builder tariffBuilder;
    private List<Tariff> tariffs;

    public List<Tariff> getTariffList() {
        return tariffs;
    }

    public void startDocument() throws SAXException {
        tariffBuilder = new Tariff.Builder();
        tariffs = new ArrayList<>();
    }

    public void endDocument() throws SAXException {
        tariffBuilder = null;
    }

    public void characters(char[] buffer, int start, int length) {
        switch (TariffTagName.getElementTagName(thisElement)) {
            case PAYROLL:
                tariffBuilder.withPayroll(Double.parseDouble(new String(buffer, start, length)));
                break;
            case SMS_PRICE:
                tariffBuilder.withSmsPrice(Double.parseDouble(new String(buffer, start, length)));
                break;
        }
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        thisElement = qName;
        switch (TariffTagName.getElementTagName(thisElement)) {
            case TARIFF:
                parseTariffAttributes(tariffBuilder, attributes);
                break;
            case CALL_PRICES:
                parseCallPrices(tariffBuilder, attributes);
                break;
            case PARAMETERS:
                parseParameters(tariffBuilder, attributes);
                break;
        }
    }

    private void parseTariffAttributes(Tariff.Builder tariffBuilder, Attributes attributes) {
        tariffBuilder.withId(Integer.parseInt(attributes.getValue("id")));
        tariffBuilder.withName(attributes.getValue("name"));
        tariffBuilder.withOperatorName(attributes.getValue("operatorName"));
    }

    private void parseCallPrices(Tariff.Builder tariffBuilder, Attributes attributes) {
        CallPrices.Builder callPricesBuilder = new CallPrices.Builder();
        callPricesBuilder.withCallsInsideNetworks(Double.parseDouble(attributes.getValue("callsInsideNetworks")));
        callPricesBuilder.withCallsInOtherNetworks(Double.parseDouble(attributes.getValue("callsInOtherNetworks")));
        callPricesBuilder.withCallsToLandLine(Double.parseDouble(attributes.getValue("callsToLandLine")));
        tariffBuilder.withCallPrices(callPricesBuilder.build());
    }

    private void parseParameters(Tariff.Builder tariffBuilder, Attributes attributes) {
        Parameters.Builder parameterBuilder = new Parameters.Builder();
        parameterBuilder.withFavouriteNumbers(Integer.parseInt(attributes.getValue("favouriteNumber")));
        parameterBuilder.withTariffication(Tariffication.valueOf(attributes.getValue("tariffication")));
        parameterBuilder.withConnectionPayment(Double.parseDouble(attributes.getValue("connectionPayment")));
        tariffBuilder.withParameters(parameterBuilder.build());
    }


    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (TariffTagName.getElementTagName(thisElement) == TariffTagName.PARAMETERS) {
            tariffs.add(tariffBuilder.build());
            tariffBuilder = new Tariff.Builder();
        }
        thisElement = "";
    }
}
