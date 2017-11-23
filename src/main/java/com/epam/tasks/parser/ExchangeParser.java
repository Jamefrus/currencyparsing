package com.epam.tasks.parser;

import com.epam.tasks.domain.Currency;

import java.net.URL;
import java.util.Map;

public interface ExchangeParser {
    Map<String, Currency> parseCurrencies(URL url) throws Exception;
}
