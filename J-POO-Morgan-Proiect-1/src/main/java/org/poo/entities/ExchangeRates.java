package org.poo.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExchangeRates {
    private final Map<CurrencyPair, Double> rates;
    private final Map<String, ArrayList<String>> currencies;

    public ExchangeRates() {
        this.rates = new HashMap<>();
        this.currencies = new HashMap<>();
    }

    public Map<CurrencyPair, Double> getRates() {
        return rates;
    }

    public Map<String, ArrayList<String>> getCurrencies() {
        return currencies;
    }
}
