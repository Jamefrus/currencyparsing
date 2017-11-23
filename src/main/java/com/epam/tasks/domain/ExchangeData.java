package com.epam.tasks.domain;

import lombok.Value;

import java.util.Map;

@Value
public class ExchangeData {
    private Map<String, Currency> currencies;

    public Currency getCurrency(String contraction) {
        return currencies.get(contraction);
    }
}
