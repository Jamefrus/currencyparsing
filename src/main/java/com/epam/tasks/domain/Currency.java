package com.epam.tasks.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Currency {
    private final String contraction;
    private final String name;
    private final String provider;
    private final double buyingRate;
    private final double sellingRate;
}
