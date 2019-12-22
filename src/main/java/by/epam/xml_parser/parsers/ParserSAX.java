package by.epam.xml_parser.parsers;

import by.epam.xml_parser.entity.Tariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class ParserSAX {
    private static Logger logger = LogManager.getLogger();

    public List<Tariff> parse(String path) {
        logger.info("SAX Parser: parsing start.");

        XMLReader reader;
        HandlerSAX handlerSAX = new HandlerSAX();

        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handlerSAX);
            reader.parse(path);
        } catch (SAXException | IOException e) {
            logger.error("SAX Parser: " + e.getMessage());
        }
        logger.info("SAX Parser: parsing end.");
        return handlerSAX.getTariffList();
    }
}
