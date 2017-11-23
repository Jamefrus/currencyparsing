package com.epam.tasks.parser;

import com.epam.tasks.domain.ExchangeData;

import java.net.URL;

public interface ExchangeParser {
    ExchangeData parse(URL url) throws Exception;
}
