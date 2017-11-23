package com.epam.tasks.html;

import com.epam.tasks.domain.Currency;
import com.epam.tasks.domain.ExchangeData;
import com.epam.tasks.parser.ExchangeParser;
import com.epam.tasks.util.Resources;
import org.junit.Test;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class MinfinHtmlExchangeDataParserTest {
    private static final Map<String, Double> EXPECTED = Stream.of("bestbank", "nbu", "midbank", "interbank", "auction").collect(Collectors.toMap(Function.identity(), s -> 1.0));
    private ExchangeParser parser = new MinfinHtmlExchangeDataParser();

    @Test
    public void parsesCurrencyWithAllRates() throws Exception {
        ExchangeData data = parser.parse(Resources.getResource("minfin.html"));

        assertThat(data).isNotNull();
        assertThat(data.getCurrencies())
                .containsEntry("UAH", Currency.builder()
                        .name("Hrywna")
                        .contraction("UAH")
                        .rates(EXPECTED).build());
    }

    @Test
    public void parsesFewCurrencies() throws Exception {
        ExchangeData data = parser.parse(Resources.getResource("minfin.html"));

        assertThat(data).isNotNull();
        assertThat(data.getCurrencies())
                .containsKeys("UAH", "USD", "RUB");
    }
}
