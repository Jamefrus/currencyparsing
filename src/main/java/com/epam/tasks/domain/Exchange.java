package com.epam.tasks.domain;

import lombok.Value;

import java.util.Map;

@Value
public class Exchange {
    private final Map<String, Currency> currencies;
}
