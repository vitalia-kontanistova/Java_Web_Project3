package by.epam.xml_parser;

import by.epam.xml_parser.entity.Tariff;
import by.epam.xml_parser.parsers.ParserDOM;
import by.epam.xml_parser.parsers.ParserSAX;
import by.epam.xml_parser.parsers.ParserStAX;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ParserDOM parser = new ParserDOM();

        List<Tariff> tariffs1 = parser.parse("src/main/resources/tariffs.xml");
        for (Tariff tariff : tariffs1) {
            System.out.println(tariff);
        }
        ParserStAX parserStAX = new ParserStAX();
        List<Tariff> tariffs2 = parserStAX.parse("src/main/resources/tariffs.xml");
        for (Tariff tariff : tariffs2) {
            System.out.println(tariff);
        }

        ParserSAX parserSAX = new ParserSAX();
        List<Tariff> tariffs3 = parserSAX.parse("src/main/resources/tariffs.xml");

        for (Tariff tariff : tariffs3) {
            System.out.println(tariff);
        }
    }
}
