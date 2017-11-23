package com.epam.tasks.parser;

import com.epam.tasks.domain.Exchange;

import java.net.URL;
import java.util.Collection;
import java.util.Currency;

public interface ExchangeParser {
    Exchange parse(URL url) throws Exception;
}
