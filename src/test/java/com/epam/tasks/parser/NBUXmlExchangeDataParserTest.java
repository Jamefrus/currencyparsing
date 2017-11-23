package com.epam.tasks.parser;

import com.epam.tasks.domain.ExchangeData;
import com.epam.tasks.parser.ExchangeParser;
import com.epam.tasks.parser.NBUXmlExchangeDataParser;
import com.epam.tasks.util.Resources;
import com.google.common.collect.ImmutableMap;
import org.junit.Before;
import org.junit.Test;

import static com.epam.tasks.domain.Currency.builder;
import static org.assertj.core.api.Assertions.assertThat;

public class NBUXmlExchangeDataParserTest {

    private static final String NBU_PROVIDER = NBUXmlExchangeDataParser.NBU_PROVIDER;
    private static final double NBU_RATE = 0.234004;
    private ExchangeParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new NBUXmlExchangeDataParser();
    }

    @Test
    public void parsesCurrencyWithNBURate() throws Exception {
        ExchangeData exchangeData = parser.parse(Resources.getResource("nbu.xml"));

        assertThat(exchangeData).isNotNull();
        assertThat(exchangeData.getCurrencies())
                .containsEntry("DZD", builder()
                        .contraction("DZD")
                        .name("Algerian Dinar")
                        .rates(ImmutableMap.of(NBU_PROVIDER, NBU_RATE))
                        .build());
    }

    @Test
    public void parsesFewCurrencies() throws Exception {
        ExchangeData exchangeData = parser.parse(Resources.getResource("nbu.xml"));

        assertThat(exchangeData).isNotNull();
        assertThat(exchangeData.getCurrencies())
                .containsKeys("AMD", "DZD", "AUD", "AZN");
    }
}