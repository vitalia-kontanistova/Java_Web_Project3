package by.epam.xml_parser.parsers;

import by.epam.xml_parser.entity.Tariff;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class ParserSAX {

    public List<Tariff> parse() {
        XMLReader reader;
        HandlerSAX handlerSAX = new HandlerSAX();

        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handlerSAX);
            reader.parse("src/main/resources/tariffs.xml");
        } catch (SAXException | IOException e) {
            //log;
            e.printStackTrace();
        }
        return handlerSAX.getTariffList();
    }

    public static void main(String[] args) {
        ParserSAX parserSAX = new ParserSAX();
        List<Tariff> tariffs = parserSAX.parse();

        for (Tariff tariff : tariffs) {
            System.out.println(tariff);
        }

    }
}
