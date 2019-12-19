package by.epam.xml_parser.parsers;

import by.epam.xml_parser.entity.CallPrices;
import by.epam.xml_parser.entity.Parameters;
import by.epam.xml_parser.entity.Tariff;
import by.epam.xml_parser.entity.Tariffication;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserDOM {

    public List<Tariff> parse() {
        List<Tariff> tariffs = new ArrayList<>();

        DOMParser domParser = new DOMParser();
        Element root;
        NodeList tariffsNodeList = null;
        try {
            domParser.parse("src/main/resources/tariffs.xml");
            Document document = domParser.getDocument();
            root = document.getDocumentElement();
            tariffsNodeList = root.getElementsByTagName("tariff");

        } catch (SAXException | IOException | NullPointerException e) {
            //log;
            e.printStackTrace();
        }

        for (int i = 0; i < tariffsNodeList.getLength(); i++) {
            Node tariffNode = tariffsNodeList.item(i);
            NamedNodeMap attributes = tariffNode.getAttributes();

            Tariff.Builder tariffBuilder = new Tariff.Builder();
            tariffBuilder.setId(Integer.parseInt(attributes.getNamedItem("id").getNodeValue()));

            tariffBuilder.setName(attributes.getNamedItem("name").getNodeValue());
            tariffBuilder.setOperatorName(attributes.getNamedItem("operatorName").getNodeValue());

            NodeList tariffInfoNode = tariffNode.getChildNodes();

            for (int j = 0; j < tariffInfoNode.getLength(); j++) {
                Node tempNode = tariffInfoNode.item(j);
                if (tempNode.getNodeName().equals("payroll")) {
                    tariffBuilder.setPayroll(Double.parseDouble(tempNode.getChildNodes().item(0).getNodeValue()));
                }
                if (tempNode.getNodeName().equals("callPrices")) {
                    attributes = tempNode.getAttributes();

                    CallPrices.Builder callPriceBuilder = new CallPrices.Builder();
                    tariffBuilder.setCallPrices(callPriceBuilder.build());
                    callPriceBuilder.setCallsInsideNetworks(Double.parseDouble(attributes.getNamedItem("callsInsideNetworks").getNodeValue()));
                    callPriceBuilder.setCallsInOtherNetworks(Double.parseDouble(attributes.getNamedItem("callsInOtherNetworks").getNodeValue()));
                    callPriceBuilder.setCallsToLandLine(Double.parseDouble(attributes.getNamedItem("callsToLandLine").getNodeValue()));
                }
                if (tempNode.getNodeName().equals("smsPrice")) {
                    tariffBuilder.setSmsPrice(Double.parseDouble(tempNode.getChildNodes().item(0).getNodeValue()));
                }
                if (tempNode.getNodeName().equals("parameters")) {
                    attributes = tempNode.getAttributes();

                    Parameters.Builder parametersBuilder = new Parameters.Builder();
                    tariffBuilder.setParameters(parametersBuilder.build());
                    parametersBuilder.setFavouriteNumbers(Integer.parseInt(attributes.getNamedItem("favouriteNumber").getNodeValue()));
                    parametersBuilder.setTariffication(Tariffication.valueOf(attributes.getNamedItem("tariffication").getNodeValue()));
                    parametersBuilder.setConnectionPayment(Double.parseDouble(attributes.getNamedItem("connectionPayment").getNodeValue()));
                }
            }
            tariffs.add(tariffBuilder.build());
        }
        return tariffs;
    }

    public static void main(String[] args) {
        ParserDOM parser = new ParserDOM();

        List<Tariff> tariffs = parser.parse();
        for (Tariff tariff : tariffs) {
            System.out.println(tariff);
        }
    }
}