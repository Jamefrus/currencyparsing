package com.epam.tasks.parser;

import com.epam.tasks.domain.Currency;
import com.epam.tasks.util.Resources;
import com.google.common.collect.ImmutableMap;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.util.Map;

import static com.epam.tasks.domain.Currency.builder;
import static org.assertj.core.api.Assertions.assertThat;

public class NBUXmlExchangeDataParserTest {

    private static final String NBU_PROVIDER = NBUXmlExchangeDataParser.NBU_PROVIDER;
    private static final double NBU_RATE = 0.234004;
    private Map<String, Currency> data;

    @Before
    public void setUp() throws Exception {
        URL resource = Resources.getResource("nbu.xml");
        data = new NBUXmlExchangeDataParser().parseCurrencies(resource);
    }

    @Test
    public void parsesCurrencyWithNBURate() throws Exception {
        assertThat(data)
                .containsEntry("DZD", builder()
                        .contraction("DZD")
                        .name("Algerian Dinar")
                        .rates(ImmutableMap.of(NBU_PROVIDER, NBU_RATE))
                        .build());
    }

    @Test
    public void parsesFewCurrencies() throws Exception {
        assertThat(data)
                .containsKeys("AMD", "DZD", "AUD", "AZN");
    }
}