package org.poo.command.debug.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import org.poo.entities.transaction.Transaction;

import java.util.ArrayList;

public class AccountHistoryDTO {
    private String IBAN;
    private double balance;
    private String currency;
    private ArrayList<Transaction> transactions;

    public AccountHistoryDTO(String IBAN, double balance, String currency, ArrayList<Transaction> transactions) {
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
}
