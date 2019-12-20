package by.epam.xml_parser.parsers;

import by.epam.xml_parser.entity.Tariff;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class ParserSAX {

    public List<Tariff> parse(String path) {
        XMLReader reader;
        HandlerSAX handlerSAX = new HandlerSAX();

        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handlerSAX);
            reader.parse(path);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return handlerSAX.getTariffList();
    }

    public static void main(String[] args) {
        ParserSAX parserSAX = new ParserSAX();
        List<Tariff> tariffs1 = parserSAX.parse("src/main/resources/tariffs.xml");

        for (Tariff tariff : tariffs1) {
            System.out.println(tariff);
        }
    }
}
