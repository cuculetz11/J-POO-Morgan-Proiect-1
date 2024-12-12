package org.poo.entities.bankAccount;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.poo.entities.CurrencyPair;
import org.poo.entities.Merchant;
import org.poo.entities.card.Card;
import org.poo.entities.transaction.Transaction;
import org.poo.entities.user.User;
import org.poo.services.AccountServices;
import org.poo.utils.Utils;

import java.sql.SQLOutput;
import java.util.*;

public abstract class Account implements AccountObserver {
    private String IBAN;
    private double balance;
    @JsonIgnore
    private double minimumBalance;
    @JsonIgnore
    private User user;
    private String currency;
    private String type;
    private LinkedHashMap<String, Card> cards;
    @JsonIgnore
    private ArrayList<Transaction> transactionsHistory;
    public Account( double balance, String currency, String type) {
        this.IBAN = Utils.generateIBAN();
        this.balance = balance;
        this.currency = currency;
        this.type = type;
        this.cards = new LinkedHashMap<>();
        this.minimumBalance = 0;
        this.transactionsHistory = new ArrayList<>();
    }

    public void transfer(Account receiver, double amount) {
        this.setBalance(this.getBalance() - amount);
        CurrencyPair currencyPair = new CurrencyPair(this.currency, receiver.getCurrency());
        AccountServices accountServices = new AccountServices();
        double exchangedAmount = accountServices.exchangeCurrency(currencyPair, amount);
        receiver.setBalance(receiver.getBalance() + exchangedAmount);
    }
    public void pay(double amount) {
        this.setBalance(this.getBalance() - amount);
    }
    @Override
    public boolean verifyBalance() {
        if(minimumBalance >= balance)
            return true;
        return false;
    }
    public abstract boolean isTransferPossible(double amount);

    @JsonGetter("cards")
    public List<Card> getCardsAsList() {
        return new ArrayList<>(cards.values());
    }
    //public abstract Transaction createTransaction(CommandInput commandInput);
    @JsonGetter("IBAN")
    public String getIBAN() {
        return IBAN;
    }

    public Map<String, Card> getCards() {
        return cards;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public void setCards(LinkedHashMap<String, Card> cards) {
        this.cards = cards;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setMinimumBalance(double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public ArrayList<Transaction> getTransactionsHistory() {
        return transactionsHistory;
    }

    public void setTransactionsHistory(ArrayList<Transaction> transactionsHistory) {
        this.transactionsHistory = transactionsHistory;
    }

}
