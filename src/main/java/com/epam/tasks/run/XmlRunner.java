package com.epam.tasks.run;

import com.epam.tasks.domain.ExchangeData;

import java.net.URL;

public class XmlRunner {

    public static void main(String[] args) throws Exception {
        URL url = new URL("http://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?xml");
        ExchangeData data = Parsers.nbuFromXml().parse(url);
        print(data);
    }

    private static void print(ExchangeData data) {
        data.getCurrencies().values().forEach(System.out::println);
    }
}
