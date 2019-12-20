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

    private static List<Tariff> process(XMLStreamReader reader) throws XMLStreamException {
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
                            tariffBuilder.setId(
                                    Integer.parseInt(reader.getAttributeValue(null, "id")));
                            tariffBuilder.setName(
                                    reader.getAttributeValue(null, "name"));
                            tariffBuilder.setOperatorName(
                                    reader.getAttributeValue(null, "operatorName"));
                            break;
                        case CALL_PRICES:
                            CallPrices.Builder callPricesBuilder = new CallPrices.Builder();
                            tariffBuilder.setCallPrices(
                                    callPricesBuilder.build());
                            callPricesBuilder.setCallsInsideNetworks(
                                    Double.parseDouble(reader.getAttributeValue(null, "callsInsideNetworks")));
                            callPricesBuilder.setCallsInOtherNetworks(
                                    Double.parseDouble(reader.getAttributeValue(null, "callsInOtherNetworks")));
                            callPricesBuilder.setCallsToLandLine(
                                    Double.parseDouble(reader.getAttributeValue(null, "callsToLandLine")));
                            break;

                        case PARAMETERS:
                            Parameters.Builder parameterBuilder = new Parameters.Builder();
                            tariffBuilder.setParameters(
                                    parameterBuilder.build());
                            parameterBuilder.setFavouriteNumbers(
                                    Integer.parseInt(reader.getAttributeValue(null, "favouriteNumber")));
                            parameterBuilder.setTariffication(
                                    Tariffication.valueOf(reader.getAttributeValue(null, "tariffication")));
                            parameterBuilder.setConnectionPayment(
                                    Double.parseDouble(reader.getAttributeValue(null, "connectionPayment")));
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
                            tariffBuilder.setPayroll(Double.parseDouble(text));
                            break;
                        case SMS_PRICE:
                            tariffBuilder.setSmsPrice(Double.parseDouble(text));
                            break;
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    elementName = TariffTagName.getElementTagName(reader.getLocalName());
                    switch (elementName) {
                        case TARIFF:
                            tariffs.add(tariffBuilder.build());
                    }
            }
        }
        return tariffs;
    }

    public static void main(String[] args) {
        ParserStAX parserStAX = new ParserStAX();
        List<Tariff> tariffs = parserStAX.parse("src/main/resources/tariffs.xml");
        for (Tariff tariff : tariffs) {
            System.out.println(tariff);
        }
    }
}
