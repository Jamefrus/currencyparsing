package com.epam.tasks.run;

import com.epam.tasks.html.MinfinHtmlExchangeDataParser;
import com.epam.tasks.json.NBUJsonExchangeDataParser;
import com.epam.tasks.parser.ExchangeParser;
import com.epam.tasks.xml.NBUXmlExchangeDataParser;
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
