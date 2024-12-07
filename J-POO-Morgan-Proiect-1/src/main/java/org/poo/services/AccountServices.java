package org.poo.services;

import org.poo.entities.Bank;

public class AccountServices {
    public void setMinBalance(String IBAN, double minBalance) {
        Bank.getInstance().getAccounts().get(IBAN).setMinimumBalance(minBalance);
    }
}
