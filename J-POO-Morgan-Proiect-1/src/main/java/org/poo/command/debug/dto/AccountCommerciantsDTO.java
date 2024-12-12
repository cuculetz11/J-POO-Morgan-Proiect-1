package org.poo.command.debug.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import org.poo.entities.Merchant;
import org.poo.entities.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

public class AccountCommerciantsDTO {
    private String IBAN;
    private double balance;
    private String currency;
    private ArrayList<Transaction> transactions;
    private List<Merchant> commerciants;

    public AccountCommerciantsDTO(String IBAN, double balance, String currency, ArrayList<Transaction> transactions, List<Merchant> commerciants) {
        this.commerciants = commerciants;
        this.IBAN = IBAN;
        this.balance = balance;
        this.currency = currency;
        this.transactions = transactions;
    }
    @JsonGetter("IBAN")
    public String getIBAN() {
        return IBAN;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public String getCurrency() {
        return currency;
    }

    public double getBalance() {
        return balance;
    }

    public List<Merchant> getCommerciants() {
        return commerciants;
    }
}
