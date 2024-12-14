package org.poo.entities;

import lombok.Getter;

@Getter
public class Merchant {
    private final String commerciant;
    private double total;

    public Merchant(final String commerciant, final double amount) {
        this.commerciant = commerciant;
        this.total = amount;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
