package com.epam.tasks.run;

public class HtmlRunner {
    public static void main(String[] args) throws Exception {
        ExchangePrinter.with()
                .parser(Parsers.minfinFromHtml())
                .url("https://minfin.com.ua/currency/")
                .print();
    }
}
