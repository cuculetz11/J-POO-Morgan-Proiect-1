package org.poo.services.initialize;

import org.poo.entities.Bank;
import org.poo.fileio.ExchangeInput;
import org.poo.services.BankMethods;

public class ExchangeRatesInitialize implements BankMethods {
    private ExchangeInput[] exchangeInput;
    public ExchangeRatesInitialize(ExchangeInput[] exchangeInput) {
        this.exchangeInput = exchangeInput;
    }
    public void visit(Bank bank) {
        for(ExchangeInput input : exchangeInput) {
            String[] fromTo = new String[2];
            fromTo[0] = input.getFrom();
            fromTo[1] = input.getTo();
            bank.getExchangeRates().put(fromTo,input.getRate());
        }
    }
}
