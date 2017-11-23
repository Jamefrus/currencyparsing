package com.epam.tasks.run;

import com.epam.tasks.parser.MinfinHtmlExchangeDataParser;
import com.epam.tasks.parser.NBUJsonExchangeDataParser;
import com.epam.tasks.parser.ExchangeParser;
import com.epam.tasks.parser.NBUXmlExchangeDataParser;
import lombok.SneakyThrows;

class Parsers {
    @SneakyThrows
    static ExchangeParser nbuFromXml() {
        return new NBUXmlExchangeDataParser();
    }

    @SneakyThrows
    static ExchangeParser nbuFromJson() {
        return new NBUJsonExchangeDataParser();
    }

    static ExchangeParser minfinFromHtml() {
        return new MinfinHtmlExchangeDataParser();
    }
}
