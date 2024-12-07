package org.poo.services.initialize;

import org.poo.entities.Bank;
import org.poo.fileio.ExchangeInput;
import org.poo.fileio.UserInput;
import org.poo.services.BankMethods;
import org.poo.services.BankingServices;

import java.util.ArrayList;

public class Initializator {
    private UserInput[] userInputs;
    private ExchangeInput[] exchangeInputs;

    public Initializator(UserInput[] userInputs, ExchangeInput[] exchangeInputs) {
        this.userInputs = userInputs;
        this.exchangeInputs = exchangeInputs;
    }
    public void initialize() {
        BankingServices bankingServices = new BankingServices();
        ArrayList<BankMethods> bankMethods = new ArrayList<>();
        bankMethods.add(new UserInitialize(userInputs));
        bankMethods.add(new ExchangeRatesInitialize(exchangeInputs));
        for(BankMethods method : bankMethods) {
           bankingServices.acceptVisitor(method);
        }
    }
}
