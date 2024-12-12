package org.poo.entities;

public class Merchant {
    private String commerciant;
    private double total;
    public Merchant(String commerciant, double amount) {
        this.commerciant = commerciant;
        this.total = amount;
    }

    public String getCommerciant() {
        return commerciant;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
