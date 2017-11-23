package com.epam.tasks.run;

public class XmlRunner {

    public static void main(String[] args) throws Exception {
        ExchangePrinter.with()
                .parser(Parsers.nbuFromXml())
                .url("http://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?xml")
                .print();
    }
}
