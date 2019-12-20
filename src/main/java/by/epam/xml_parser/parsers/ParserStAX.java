package by.epam.xml_parser.parsers;

import by.epam.xml_parser.entity.*;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ParserStAX {

    private List<Tariff> parse(String path) {
        List<Tariff> tariffs = new ArrayList<>();

        XMLInputFactory inputFactory = XMLInputFactory.newFactory();
        try {
            InputStream inputStream = new FileInputStream(path);
            XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);
            tariffs = process(reader);

        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return tariffs;
    }

    private List<Tariff> process(XMLStreamReader reader) throws XMLStreamException {
        List<Tariff> tariffs = new ArrayList<>();
        Tariff.Builder tariffBuilder = new Tariff.Builder();
        TariffTagName elementName = null;

        while (reader.hasNext()) {

            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:

                    elementName = TariffTagName.getElementTagName(reader.getLocalName());
                    switch (elementName) {
                        case TARIFF:
                            tariffBuilder = new Tariff.Builder();
                            parseTariffAttributes(tariffBuilder, reader);
                            break;
                        case CALL_PRICES:
                            parseCallPrices(tariffBuilder, reader);
                            break;
                        case PARAMETERS:
                            parseParameters(tariffBuilder, reader);
                            break;
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:

                    String text = reader.getText().trim();
                    if (text.isEmpty()) {
                        break;
                    }
                    switch (elementName) {
                        case PAYROLL:
                            tariffBuilder.withPayroll(Double.parseDouble(text));
                            break;
                        case SMS_PRICE:
                            tariffBuilder.withSmsPrice(Double.parseDouble(text));
                            break;
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:

                    if (TariffTagName.getElementTagName(reader.getLocalName()) == TariffTagName.TARIFF) {
                        tariffs.add(tariffBuilder.build());
                    }
            }
        }
        return tariffs;
    }

    private void parseTariffAttributes(Tariff.Builder tariffBuilder, XMLStreamReader reader) {
        tariffBuilder.withId(Integer.parseInt(reader.getAttributeValue(null, "id")));
        tariffBuilder.withName(reader.getAttributeValue(null, "name"));
        tariffBuilder.withOperatorName(reader.getAttributeValue(null, "operatorName"));
    }

    private void parseCallPrices(Tariff.Builder tariffBuilder, XMLStreamReader reader) {
        CallPrices.Builder callPricesBuilder = new CallPrices.Builder();
        callPricesBuilder.withCallsInsideNetworks(Double.parseDouble(reader.getAttributeValue(null, "callsInsideNetworks")));
        callPricesBuilder.withCallsInOtherNetworks(Double.parseDouble(reader.getAttributeValue(null, "callsInOtherNetworks")));
        callPricesBuilder.withCallsToLandLine(Double.parseDouble(reader.getAttributeValue(null, "callsToLandLine")));
        tariffBuilder.withCallPrices(callPricesBuilder.build());
    }

    private void parseParameters(Tariff.Builder tariffBuilder, XMLStreamReader reader) {
        Parameters.Builder parameterBuilder = new Parameters.Builder();
        parameterBuilder.withFavouriteNumbers(Integer.parseInt(reader.getAttributeValue(null, "favouriteNumber")));
        parameterBuilder.withTariffication(Tariffication.valueOf(reader.getAttributeValue(null, "tariffication")));
        parameterBuilder.withConnectionPayment(Double.parseDouble(reader.getAttributeValue(null, "connectionPayment")));
        tariffBuilder.withParameters(parameterBuilder.build());
    }

    public static void main(String[] args) {
        ParserStAX parserStAX = new ParserStAX();
        List<Tariff> tariffs = parserStAX.parse("src/main/resources/tariffs.xml");
        for (Tariff tariff : tariffs) {
            System.out.println(tariff);
        }
    }
}
