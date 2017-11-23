package com.epam.tasks.run;

import java.net.URL;

public class JsonRunner {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json");
        System.out.println(Parsers.nbuFromJson().parse(url));
    }
}

