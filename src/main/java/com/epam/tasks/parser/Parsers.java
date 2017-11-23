package com.epam.tasks.parser;

import lombok.SneakyThrows;

public class Parsers {
    @SneakyThrows
    public static ExchangeParser nbuFromXml() {
        return new NBUXmlExchangeDataParser();
    }

    @SneakyThrows
    public static ExchangeParser nbuFromJson() {
        return new NBUJsonExchangeDataParser();
    }

    public static ExchangeParser minfinFromHtml() {
        return new MinfinHtmlExchangeDataParser();
    }
}
