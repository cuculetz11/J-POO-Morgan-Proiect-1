package org.poo.services;

import org.poo.entities.Bank;
import org.poo.entities.Merchant;
import org.poo.entities.bankAccount.Account;
import org.poo.entities.CurrencyPair;
import org.poo.entities.bankAccount.SavingsAccount;
import org.poo.entities.transaction.Transaction;


import javax.xml.transform.stax.StAXResult;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class AccountServices {
    public void setMinBalance(String IBAN, double minBalance) {
        Bank.getInstance().getAccounts().get(IBAN).setMinimumBalance(minBalance);
    }
    public double exchangeCurrency(CurrencyPair currencyPair, double amount) {
        if(currencyPair.getFrom().equals(currencyPair.getTo())) {
            return amount;
        }
        Map<CurrencyPair, Double> rates = Bank.getInstance().getExchangeRates().getRates();
        Map<String, ArrayList<String>> currencies = Bank.getInstance().getExchangeRates().getCurrencies();
        if(rates.containsKey(currencyPair)) {
            double rate = rates.get(currencyPair);
            return rates.get(currencyPair) * amount;
        }
        HashSet<String> visited = new HashSet<>();
        exchangeCurrencyDFS(currencyPair.getFrom(), currencyPair.getTo(), visited, 1, rates, currencies,currencyPair);
        double rate = rates.get(currencyPair);
        CurrencyPair inverse = new CurrencyPair(currencyPair.getTo(), currencyPair.getFrom());
        rates.put(inverse, 1/rate);
        if(!currencies.containsKey(inverse.getFrom())) {
            currencies.put(inverse.getFrom(), new ArrayList<>());
        }
        currencies.get(inverse.getFrom()).add(inverse.getTo());
        return amount * rate;
    }
    /*
    Adaug direct in banca rate-ul gasit precum si noua legatura pentru ca poate am nevoie mai tarziu iarasi de ea si sa nu fac iar dfs
     */
    public void exchangeCurrencyDFS(String from, String to, HashSet<String> visited, double rate,  Map<CurrencyPair, Double> rates, Map<String, ArrayList<String>> currencies, CurrencyPair searchedPair) {
        visited.add(from);
        if (from.equals(to)) {
            rates.put(searchedPair, rate);
            if(!currencies.containsKey(searchedPair.getFrom())) {
                currencies.put(searchedPair.getFrom(), new ArrayList<>());
            }
            currencies.get(searchedPair.getFrom()).add(to);
            return;
        }
        ArrayList<String> neighbours = currencies.get(from);
        for(String neighbour : neighbours) {
            if (!visited.contains(neighbour)) {
                CurrencyPair currencyPair = new CurrencyPair(from, neighbour);
                double newRate = rate * rates.get(currencyPair);
                exchangeCurrencyDFS(neighbour,to,visited,newRate,rates,currencies,searchedPair);
                //am pus perechea cautata deja n map
                if (rates.containsKey(searchedPair)) {
                    return;
                }
            }
        }

    }
    public void setAlias(String userEmail, String IBAN, String alias) {
        Account account = Bank.getInstance().getUsers().get(userEmail).getAccounts().get(IBAN);
        Bank.getInstance().getUsers().get(userEmail).getAccounts().put(alias, account);
        Bank.getInstance().getAccounts().put(alias, account);
    }
    public void addTransactionToHistory(String IBAN, Transaction transaction) {
        Account account = Bank.getInstance().getAccounts().get(IBAN);
        account.getTransactionsHistory().add(transaction);
    }
}
