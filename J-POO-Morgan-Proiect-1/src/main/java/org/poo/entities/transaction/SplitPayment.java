package org.poo.entities.transaction;

import java.util.ArrayList;
import java.util.List;

public class SplitPayment extends Transaction{
    private String currency;
    private double amount;
    private List<String> involvedAccounts;
    public SplitPayment(int timestamp, String currency, double amount, List<String> involvedAccounts, String description) {
        super(timestamp,description);
        this.currency = currency;
        this.amount = amount;
        this.involvedAccounts = involvedAccounts;
    }

    public String getCurrency() {
        return currency;
    }

    public List<String> getInvolvedAccounts() {
        return involvedAccounts;
    }

    public double getAmount() {
        return amount;
    }
}
