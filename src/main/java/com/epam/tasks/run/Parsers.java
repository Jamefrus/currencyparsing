package com.epam.tasks.run;

import com.epam.tasks.xml.NBUXmlExchangeDataParser;
import lombok.SneakyThrows;

class Parsers {
    @SneakyThrows
    static NBUXmlExchangeDataParser nbuFromXml() {
        return new NBUXmlExchangeDataParser();
    }
}
