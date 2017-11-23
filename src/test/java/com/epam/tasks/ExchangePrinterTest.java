package com.epam.tasks;

import com.epam.tasks.ExchangePrinter;
import com.epam.tasks.parser.ExchangeParser;
import com.epam.tasks.parser.Parsers;
import org.junit.Test;

public class ExchangePrinterTest {
    @Test
    public void printsHtmlFromMinfin() throws Exception {
        ExchangeParser parser = Parsers.minfinFromHtml();

        ExchangePrinter.with()
                .parser(parser)
                .url("https://minfin.com.ua/currency/")
                .print();
    }


    @Test
    public void printsXmlFromNBU() throws Exception {
        ExchangeParser parser = Parsers.nbuFromXml();

        ExchangePrinter.with()
                .parser(parser)
                .url("http://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?xml")
                .print();
    }


    @Test
    public void printsJsonFromNBU() throws Exception {
        ExchangeParser parser = Parsers.nbuFromJson();

        ExchangePrinter.with()
                .parser(parser)
                .url("http://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json")
                .print();
    }
}
