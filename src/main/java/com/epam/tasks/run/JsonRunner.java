package com.epam.tasks.run;

public class JsonRunner {
    public static void main(String[] args) throws Exception {
        ExchangePrinter.with()
                .parser(Parsers.nbuFromJson())
                .url("http://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json")
                .print();
    }
}

