package com.epam.tasks.parser;

import com.epam.tasks.domain.Currency;
import com.google.common.collect.ImmutableMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@SuppressWarnings({"unused", "MismatchedQueryAndUpdateOfCollection"})
class NBUXmlExchangeDataParser implements ExchangeParser {

    public static final String NBU_PROVIDER = "NBU";
    private Unmarshaller unmarshaller;

    public NBUXmlExchangeDataParser() throws JAXBException {
        unmarshaller = JAXBContext.newInstance(XmlExchange.class).createUnmarshaller();
    }

    public Map<String, Currency> parseCurrencies(URL url) throws JAXBException {
        return groupByContractions((XmlExchange) unmarshaller.unmarshal(url));
    }

    private Map<String, Currency> groupByContractions(XmlExchange xmlExchange) {
        return xmlExchange.currencies.stream()
                .map(XmlCurrency::toCurrency)
                .collect(Collectors.toMap(Currency::getContraction, Function.identity()));
    }

    @XmlRootElement(name = "exchange")
    private static class XmlExchange {
        @XmlElement(name = "currency")
        private Set<XmlCurrency> currencies;
    }

    @XmlRootElement(name = "currency")
    private static class XmlCurrency {
        @XmlElement(name = "cc")
        private String contraction;

        @XmlElement(name = "txt")
        private String name;

        @XmlElement(name = "rate")
        private Double rate;

        Currency toCurrency() {
            return Currency.builder()
                    .contraction(contraction)
                    .name(name)
                    .rates(ImmutableMap.of(NBU_PROVIDER, rate))
                    .build();
        }
    }
}
