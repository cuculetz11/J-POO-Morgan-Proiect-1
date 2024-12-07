package org.poo.entities.BankAccount;

import org.poo.fileio.CommandInput;

public class SavingsAccount extends Account {
    private double interestRate;
    public SavingsAccount(CommandInput input) {
        super(0, input.getCurrency(), "savings");
        this.interestRate = input.getInterestRate();
    }
}
