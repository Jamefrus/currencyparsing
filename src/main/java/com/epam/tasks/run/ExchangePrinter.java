package com.epam.tasks.run;

import com.epam.tasks.parser.ExchangeParser;
import lombok.Builder;

import java.net.URL;

class ExchangePrinter {

    @Builder(builderMethodName = "with", buildMethodName = "print")
    static void print(String url, ExchangeParser parser) throws Exception {
        parser.parse(new URL(url)).getCurrencies().values().forEach(System.out::println);
    }
}
