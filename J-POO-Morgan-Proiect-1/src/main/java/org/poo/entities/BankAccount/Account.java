package org.poo.entities.BankAccount;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.poo.entities.card.Card;
import org.poo.entities.transaction.Transaction;
import org.poo.entities.user.User;
import org.poo.fileio.CommandInput;
import org.poo.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Account implements AccountObserver {
    private String IBAN;
    private double balance;
    @JsonIgnore
    private double minimumBalance;
    @JsonIgnore
    private User user;
    private String currency;
    private String type;
    private Map<String, Card> cards;
    public Account( double balance, String currency, String type) {
        this.IBAN = Utils.generateIBAN();
        this.balance = balance;
        this.currency = currency;
        this.type = type;
        this.cards = new HashMap<>();
        this.minimumBalance = -1;
    }

    @Override
    public void verifyBalance() {

    }

    @JsonGetter("cards")
    public List<Card> getCardsAsList() {
        return new ArrayList<>(cards.values()).reversed();
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

    public void setCards(Map<String, Card> cards) {
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
}
