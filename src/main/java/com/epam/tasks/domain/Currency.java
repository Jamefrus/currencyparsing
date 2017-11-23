package com.epam.tasks.domain;

import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Value
@Builder
public class Currency {
    private final String contraction;
    private final String name;
    private final Map<String, Double> rates;
}
