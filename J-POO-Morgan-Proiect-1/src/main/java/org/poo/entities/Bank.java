package org.poo.entities;

import org.poo.entities.BankAccount.Account;
import org.poo.entities.transaction.Transaction;
import org.poo.entities.user.User;
import org.poo.services.BankMethods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bank {
    private static Bank instance = null;
    private Map<String, User> users;
    private Map<String, Account> accounts;
    private Map<String[],Double> exchangeRates;
    private Map<String, ArrayList<Transaction>> transactionHistory;
    private Bank() {
        users = new HashMap<>();
        exchangeRates = new HashMap<>();
        transactionHistory = new HashMap<>();
        accounts = new HashMap<>();
    }
    public static Bank getInstance() {
        if (instance == null) {
            instance = new Bank();
        }
        return instance;
    }
    public void reset() {
        users = new HashMap<>();
        accounts = new HashMap<>();
        exchangeRates = new HashMap<>();
        transactionHistory = new HashMap<>();
    }
    public Map<String, User> getUsers() {
        return users;
    }
    public void setUsers(Map<String, User> users) {
        this.users = users;
    }
    public Map<String[],Double> getExchangeRates() {
        return exchangeRates;
    }
    public void setExchangeRates(Map<String[],Double> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

    public Map<String, ArrayList<Transaction>> getTransactionHistory() {
        return transactionHistory;
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }
}
