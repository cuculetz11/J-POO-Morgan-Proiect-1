package org.poo.entities.bankAccount;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.poo.fileio.CommandInput;

public class SavingsAccount extends Account {
    @JsonIgnore
    private double interestRate;
    public SavingsAccount(CommandInput input) {
        super(0, input.getCurrency(), "savings");
        this.interestRate = input.getInterestRate();
    }

    @Override
    public boolean isTransferPossible(double amount) {
        if(this.getBalance() > amount) {
            return true;
        }
        return false;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }
}
