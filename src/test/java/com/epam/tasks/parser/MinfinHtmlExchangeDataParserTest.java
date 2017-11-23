package com.epam.tasks.parser;

import com.epam.tasks.domain.Currency;
import com.epam.tasks.util.Resources;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class MinfinHtmlExchangeDataParserTest {
    private static final Map<String, Double> EXPECTED = Stream.of("bestbank", "nbu", "midbank", "interbank", "auction").collect(Collectors.toMap(Function.identity(), s -> 1.0));
    private Map<String, Currency> data;

    @Before
    public void setUp() throws Exception {
        URL resource = Resources.getResource("minfin.html");
        data = new MinfinHtmlExchangeDataParser().parseCurrencies(resource);
    }

    @Test
    public void parsesCurrencyWithAllRates() throws Exception {
        assertThat(data)
                .containsEntry("UAH", Currency.builder()
                        .name("Hrywna")
                        .contraction("UAH")
                        .rates(EXPECTED).build());
    }

    @Test
    public void parsesFewCurrencies() throws Exception {
        assertThat(data).containsKeys("UAH", "USD", "RUB");
    }
}
