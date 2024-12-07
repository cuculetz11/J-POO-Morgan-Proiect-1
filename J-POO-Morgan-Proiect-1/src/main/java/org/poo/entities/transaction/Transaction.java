package org.poo.entities.transaction;

public abstract class Transaction {
    private int timestamp;
    private String description;
    public Transaction(int timestamp, String description) {
        this.timestamp = timestamp;
        this.description = description;
    }
}
