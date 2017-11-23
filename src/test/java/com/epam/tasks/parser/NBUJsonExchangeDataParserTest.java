package com.epam.tasks.parser;

import com.epam.tasks.domain.Currency;
import com.epam.tasks.util.Resources;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.util.Map;

import static com.epam.tasks.parser.NBUXmlExchangeDataParser.NBU_PROVIDER;
import static com.google.common.collect.ImmutableMap.of;
import static org.assertj.core.api.Assertions.assertThat;

public class NBUJsonExchangeDataParserTest {
    private static final Double NBU_RATE = 15.799329;
    private Map<String, Currency> data;

    @Before
    public void setUp() throws Exception {
        URL resource = Resources.getResource("nbu.json");
        data = new NBUJsonExchangeDataParser().parseCurrencies(resource);
    }

    @Test
    public void parsesCurrencyFullyWithNBU() throws Exception {
        assertThat(data)
                .containsEntry("AZN", Currency.builder()
                        .name("azn")
                        .contraction("AZN")
                        .rates(of(NBU_PROVIDER, NBU_RATE))
                        .build());
    }

    @Test
    public void parsesFewCurrencies() throws Exception {
        assertThat(data)
                .containsKeys("USD", "CAD", "BGN", "AZN");
    }
}
