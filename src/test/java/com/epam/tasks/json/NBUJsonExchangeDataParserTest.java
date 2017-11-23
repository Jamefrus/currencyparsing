package com.epam.tasks.json;

import com.epam.tasks.domain.Currency;
import com.epam.tasks.domain.ExchangeData;
import com.epam.tasks.parser.ExchangeParser;
import com.epam.tasks.util.Resources;
import org.junit.Test;

import static com.epam.tasks.xml.NBUXmlExchangeDataParser.NBU_PROVIDER;
import static com.google.common.collect.ImmutableMap.of;
import static org.assertj.core.api.Assertions.assertThat;

public class NBUJsonExchangeDataParserTest {
    private static final Double NBU_RATE = 15.799329;
    private ExchangeParser parser = new NBUJsonExchangeDataParser();

    @Test
    public void parsesCurrencyFullyWithNBU() throws Exception {
        ExchangeData data = parser.parse(Resources.getResource("nbu.json"));

        assertThat(data).isNotNull();
        assertThat(data.getCurrencies())
                .containsEntry("AZN", Currency.builder()
                        .name("azn")
                        .contraction("AZN")
                        .rates(of(NBU_PROVIDER, NBU_RATE))
                        .build());
    }

    @Test
    public void parsesFewCurrencies() throws Exception {
        ExchangeData data = parser.parse(Resources.getResource("nbu.json"));

        assertThat(data).isNotNull();
        assertThat(data.getCurrencies())
                .containsKeys("USD", "CAD", "BGN", "AZN");
    }
}
