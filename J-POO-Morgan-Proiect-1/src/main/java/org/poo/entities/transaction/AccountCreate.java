package org.poo.entities.transaction;

public class AccountCreate extends Transaction {
    public AccountCreate(int timestamp) {
        super(timestamp,"New account created");
    }
}
