package com.epam.tasks;

import com.epam.tasks.parser.ExchangeParser;
import lombok.Builder;

import java.net.URL;

class ExchangePrinter {

    @Builder(builderMethodName = "with", buildMethodName = "print")
    static void print(String url, ExchangeParser parser) throws Exception {
        parser.parseCurrencies(new URL(url)).values().forEach(System.out::println);
    }
}
