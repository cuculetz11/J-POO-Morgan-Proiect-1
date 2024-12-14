package org.poo.entities.bankAccount;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Setter;
import org.poo.entities.CurrencyPair;
import org.poo.entities.card.Card;
import org.poo.entities.transaction.Transaction;
import org.poo.entities.user.User;
import org.poo.services.AccountServices;
import org.poo.utils.Utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Setter
public abstract class Account implements AccountObserver {
    private String iban;
    private double balance;
    private String currency;
    private String type;
    private LinkedHashMap<String, Card> cards;
    @JsonIgnore
    private User user;
    @JsonIgnore
    private double minimumBalance;
    @JsonIgnore
    private ArrayList<Transaction> transactionsHistory;

    public Account(final double balance, final String currency, final String type) {
        this.iban = Utils.generateIBAN();
        this.balance = balance;
        this.currency = currency;
        this.type = type;
        this.cards = new LinkedHashMap<>();
        this.minimumBalance = 0;
        this.transactionsHistory = new ArrayList<>();
    }

    /**
     * Realizeaza transferul unei sume din contul curent in contul receiveru-ului
     * @param receiver contul celui ce primeste suma de bani
     * @param amount suma trasferata
     */
    public void transfer(final Account receiver, final double amount) {
        this.setBalance(this.getBalance() - amount);
        CurrencyPair currencyPair = new CurrencyPair(this.currency, receiver.getCurrency());
        AccountServices accountServices = new AccountServices();
        double exchangedAmount = accountServices.exchangeCurrency(currencyPair, amount);
        receiver.setBalance(receiver.getBalance() + exchangedAmount);
    }

    /**
     * Realizeaza o plata
     * @param amount suma platii
     */
    public void pay(double amount) {
        this.setBalance(this.getBalance() - amount);
    }

    @Override
    public boolean verifyBalance() {
        return minimumBalance >= balance;
    }

    public abstract boolean isTransferPossible(double amount);

    @JsonGetter("cards")
    public List<Card> getCardsAsList() {
        return new ArrayList<>(cards.values());
    }

    //public abstract Transaction createTransaction(CommandInput commandInput);
    @JsonGetter("IBAN")
    public String getIban() {
        return iban;
    }

    public Map<String, Card> getCards() {
        return cards;
    }

    public double getBalance() {
        return balance;
    }

    public String getType() {
        return type;
    }

    public String getCurrency() {
        return currency;
    }

    public User getUser() {
        return user;
    }

    public ArrayList<Transaction> getTransactionsHistory() {
        return transactionsHistory;
    }

}
