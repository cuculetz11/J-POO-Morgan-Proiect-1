package org.poo.entities.transaction;

public class CardPayment extends Transaction {
    private double amount;
    private String commerciant;

    public CardPayment(double amount, String commerciant, int timestamp) {
        super(timestamp,"Card payment");
        this.amount = amount;
        this.commerciant = commerciant;
    }

    public double getAmount() {
        return amount;
    }

    public String getCommerciant() {
        return commerciant;
    }
}
