package org.poo.entities.bankAccount;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.poo.fileio.CommandInput;

@Setter
@Getter
public class SavingsAccount extends Account {
    @JsonIgnore
    private double interestRate;

    public SavingsAccount(final CommandInput input) {
        super(0, input.getCurrency(), "savings");
        this.interestRate = input.getInterestRate();
    }

    @Override
    public boolean isTransferPossible(double amount) {
        return !(this.getBalance() > amount);
    }

}
