package com.epam.tasks.html;

import com.epam.tasks.domain.Currency;
import com.epam.tasks.domain.ExchangeData;
import com.epam.tasks.parser.ExchangeParser;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MinfinHtmlExchangeDataParser implements ExchangeParser {

    @Override
    public ExchangeData parse(URL url) throws Exception {
        Map<String, Currency> currencies = parseCurrencies(url);
        return new ExchangeData(currencies);
    }

    private Map<String, Currency> parseCurrencies(URL url) throws Exception {
        try (BufferedReader reader = reader(url)) {
            return reader.lines()
                    .filter(s -> s.contains("curWgtJSON"))
                    .findFirst()
                    .map(this::extractJson)
                    .map(this::parseJson)
                    .map(this::getCurrencies)
                    .orElse(Collections.emptyMap());
        }
    }

    private BufferedReader reader(URL url) throws IOException {
        URLConnection connection = url.openConnection();
        connection.addRequestProperty("User-Agent", "Magic Parser");
        return new BufferedReader(new InputStreamReader(connection.getInputStream()));
    }

    private String extractJson(String s) {
        return StringUtils.substringBetween(s, "=", ";").trim();
    }

    private JsonObject parseJson(String content) {
        return new Gson().fromJson(content, JsonObject.class);
    }

    private Map<String, Currency> getCurrencies(JsonObject object) {
        return object.keySet().stream()
                .map(contraction -> build(object, contraction))
                .collect(Collectors.toMap(Currency::getContraction, Function.identity()));
    }

    private Currency build(JsonObject object, String contraction) {
        JsonObject jsonCurrency = object.getAsJsonObject(contraction);
        return Currency.builder()
                .name(getReadableName(jsonCurrency))
                .rates(getRates(jsonCurrency))
                .contraction(contraction)
                .build();
    }

    private String getReadableName(JsonObject jsonCurrency) {
        return jsonCurrency.get("readableName").getAsString();
    }

    private Map<String, Double> getRates(JsonObject jsonCurrency) {
        return jsonCurrency.getAsJsonObject("buy")
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, this::getRate));
    }

    private Double getRate(Map.Entry<String, JsonElement> value) {
        JsonElement element = value.getValue();
        if (!element.isJsonNull()) {
            String string = element.getAsString();
            if (!string.isEmpty()) {
                return element.getAsDouble();
            }
        }
        return Double.NaN;
    }

}
