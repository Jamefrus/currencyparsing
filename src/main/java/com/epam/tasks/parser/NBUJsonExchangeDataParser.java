package com.epam.tasks.parser;

import com.epam.tasks.domain.Currency;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import lombok.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.epam.tasks.parser.NBUXmlExchangeDataParser.NBU_PROVIDER;

class NBUJsonExchangeDataParser implements ExchangeParser {

    private static final Type TYPE = new TypeToken<Set<JsonCurrency>>() {
    }.getType();

    public Map<String, Currency> parseCurrencies(URL url) throws IOException {
        try (Reader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            Set<JsonCurrency> object = new Gson().fromJson(reader, TYPE);
            return extractCurrencies(object);
        }
    }

    private Map<String, Currency> extractCurrencies(Set<JsonCurrency> currencies) {
        return currencies.stream().map(JsonCurrency::toCurrency)
                .collect(Collectors.toMap(Currency::getContraction, Function.identity()));
    }

    @Value
    private static class JsonCurrency {
        @SerializedName("txt")
        private String name;

        @SerializedName("cc")
        private String contraction;

        @SerializedName("rate")
        private Double rate;

        Currency toCurrency() {
            return Currency.builder()
                    .name(name)
                    .contraction(contraction)
                    .rates(ImmutableMap.of(NBU_PROVIDER, rate))
                    .build();
        }
    }
}
