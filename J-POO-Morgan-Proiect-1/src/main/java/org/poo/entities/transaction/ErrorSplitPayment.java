package org.poo.entities.transaction;

import java.util.List;

public class ErrorSplitPayment extends Transaction {
    private String currency;
    private double amount;
    private String error;
    private List<String> involvedAccounts;
    public ErrorSplitPayment(int timestamp, String currency, double amount, List<String> involvedAccounts, String description, String error) {
        super(timestamp,description);
        this.currency = currency;
        this.amount = amount;
        this.involvedAccounts = involvedAccounts;
        this.error = error;
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

    public String getError() {
        return error;
    }
}
