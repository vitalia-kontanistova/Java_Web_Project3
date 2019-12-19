package by.epam.xml_parser.parsers;

import by.epam.xml_parser.entity.CallPrices;
import by.epam.xml_parser.entity.Parameters;
import by.epam.xml_parser.entity.Tariff;
import by.epam.xml_parser.entity.Tariffication;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class HandlerSAX extends DefaultHandler {
    private String thisElement;
    private Tariff.Builder tariffBuilder;
    private List<Tariff> tariffs;

    public List<Tariff> getTariffList(){
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
        switch (thisElement) {
            case "payroll":
                tariffBuilder.setPayroll(Double.parseDouble(new String(buffer, start, length)));
                break;
            case "smsPrice":
                tariffBuilder.setSmsPrice(Double.parseDouble(new String(buffer, start, length)));
                break;
        }
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        thisElement = qName;
        switch (thisElement) {
            case "tariff":
                tariffBuilder.setId(Integer.parseInt(attributes.getValue("id")));
                tariffBuilder.setName(attributes.getValue("name"));
                tariffBuilder.setOperatorName(attributes.getValue("operatorName"));
                break;

            case "callPrices":
                CallPrices.Builder callPricesBuilder = new CallPrices.Builder();
                tariffBuilder.setCallPrices(callPricesBuilder.build());
                callPricesBuilder.setCallsInsideNetworks(Double.parseDouble(attributes.getValue("callsInsideNetworks")));
                callPricesBuilder.setCallsInOtherNetworks(Double.parseDouble(attributes.getValue("callsInOtherNetworks")));
                callPricesBuilder.setCallsToLandLine(Double.parseDouble(attributes.getValue("callsToLandLine")));
                break;

            case "parameters":
                Parameters.Builder parameterBuilder = new Parameters.Builder();
                tariffBuilder.setParameters(parameterBuilder.build());
                parameterBuilder.setFavouriteNumbers(Integer.parseInt(attributes.getValue("favouriteNumber")));
                parameterBuilder.setTariffication(Tariffication.valueOf(attributes.getValue("tariffication")));
                parameterBuilder.setConnectionPayment(Double.parseDouble(attributes.getValue("connectionPayment")));
                break;
        }

    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (thisElement.equals("parameters")) {
            tariffs.add(tariffBuilder.build());
            tariffBuilder = new Tariff.Builder();
        }
        thisElement = "";
    }
}
