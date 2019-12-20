package by.epam.xml_parser.parsers;

import by.epam.xml_parser.entity.*;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserDOM {

    public List<Tariff> parse(String path) {
        List<Tariff> tariffs = new ArrayList<>();

        DOMParser domParser = new DOMParser();
        Element root;
        NodeList tariffsNodeList = null;
        try {
            domParser.parse(path);
            Document document = domParser.getDocument();
            root = document.getDocumentElement();
            tariffsNodeList = root.getElementsByTagName("tariff");

        } catch (SAXException | IOException | NullPointerException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < tariffsNodeList.getLength(); i++) {
            Node tariffNode = tariffsNodeList.item(i);
            NamedNodeMap attributes = tariffNode.getAttributes();

            Tariff.Builder tariffBuilder = new Tariff.Builder();
            parseTariffAttributes(tariffBuilder, attributes);

            NodeList tariffInfoNode = tariffNode.getChildNodes();
            for (int j = 0; j < tariffInfoNode.getLength(); j++) {
                Node tempNode = tariffInfoNode.item(j);

                switch (TariffTagName.getElementTagName(tempNode.getNodeName())) {
                    case PAYROLL:
                        tariffBuilder.withPayroll(Double.parseDouble(tempNode.getChildNodes().item(0).getNodeValue()));
                        break;
                    case CALL_PRICES:
                        attributes = tempNode.getAttributes();
                        parseCallPrices(tariffBuilder, attributes);
                        break;
                    case SMS_PRICE:
                        tariffBuilder.withSmsPrice(Double.parseDouble(tempNode.getChildNodes().item(0).getNodeValue()));
                        break;
                    case PARAMETERS:
                        attributes = tempNode.getAttributes();
                        parseParameters(tariffBuilder, attributes);
                        break;
                    default:
                        break;
                }
            }
            tariffs.add(tariffBuilder.build());
        }
        return tariffs;
    }

    private void parseTariffAttributes(Tariff.Builder tariffBuilder, NamedNodeMap attributes) {
        tariffBuilder.withId(Integer.parseInt(attributes.getNamedItem("id").getNodeValue()));
        tariffBuilder.withName(attributes.getNamedItem("name").getNodeValue());
        tariffBuilder.withOperatorName(attributes.getNamedItem("operatorName").getNodeValue());
    }

    private void parseCallPrices(Tariff.Builder tariffBuilder, NamedNodeMap attributes) {
        CallPrices.Builder callPriceBuilder = new CallPrices.Builder();
        callPriceBuilder.withCallsInsideNetworks(Double.parseDouble(attributes.getNamedItem("callsInsideNetworks").getNodeValue()));
        callPriceBuilder.withCallsInOtherNetworks(Double.parseDouble(attributes.getNamedItem("callsInOtherNetworks").getNodeValue()));
        callPriceBuilder.withCallsToLandLine(Double.parseDouble(attributes.getNamedItem("callsToLandLine").getNodeValue()));
        tariffBuilder.withCallPrices(callPriceBuilder.build());
    }

    private void parseParameters(Tariff.Builder tariffBuilder, NamedNodeMap attributes) {
        Parameters.Builder parametersBuilder = new Parameters.Builder();
        parametersBuilder.withFavouriteNumbers(Integer.parseInt(attributes.getNamedItem("favouriteNumber").getNodeValue()));
        parametersBuilder.withTariffication(Tariffication.valueOf(attributes.getNamedItem("tariffication").getNodeValue()));
        parametersBuilder.withConnectionPayment(Double.parseDouble(attributes.getNamedItem("connectionPayment").getNodeValue()));
        tariffBuilder.withParameters(parametersBuilder.build());
    }

    public static void main(String[] args) {
        ParserDOM parser = new ParserDOM();

        List<Tariff> tariffs = parser.parse("src/main/resources/tariffs.xml");
        for (Tariff tariff : tariffs) {
            System.out.println(tariff);
        }
    }
}