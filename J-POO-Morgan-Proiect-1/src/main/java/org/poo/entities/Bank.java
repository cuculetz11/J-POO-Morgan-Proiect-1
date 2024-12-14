package org.poo.entities;

import org.poo.entities.bankAccount.Account;
import org.poo.entities.card.Card;
import org.poo.entities.transaction.Transaction;
import org.poo.entities.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public final class Bank {
    private static Bank instance = null;
    private LinkedHashMap<String, User> users;
    private Map<String, Account> accounts;
    private Map<String, Card> cards;
    private ExchangeRates exchangeRates;
    private Map<String, ArrayList<Transaction>> transactionHistory;
    private Map<String, Card> cardDeletedHistory;
    private int currentTimestamp;

    private Bank() {
        users = new LinkedHashMap<>();
        exchangeRates = new ExchangeRates();
        transactionHistory = new HashMap<>();
        accounts = new HashMap<>();
        cards = new HashMap<>();
        cardDeletedHistory = new HashMap<>();
    }

    public static Bank getInstance() {
        if (instance == null) {
            instance = new Bank();
        }
        return instance;
    }

    public void reset() {
        users = new LinkedHashMap<>();
        accounts = new HashMap<>();
        exchangeRates = new ExchangeRates();
        transactionHistory = new HashMap<>();
        cards = new HashMap<>();
        cardDeletedHistory = new HashMap<>();
    }

    public int getCurrentTimestamp() {
        return currentTimestamp;
    }

    public void setCurrentTimestamp(int currentTimestamp) {
        this.currentTimestamp = currentTimestamp;
    }

    public LinkedHashMap<String, User> getUsers() {
        return users;
    }

    public ExchangeRates getExchangeRates() {
        return exchangeRates;
    }

    public Map<String, ArrayList<Transaction>> getTransactionHistory() {
        return transactionHistory;
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public Map<String, Card> getCards() {
        return cards;
    }

    public Map<String, Card> getCardDeletedHistory() {
        return cardDeletedHistory;
    }
}
