package org.poo.entities.bankAccount;

import org.poo.fileio.CommandInput;

public class ClassicAcount extends Account {
    public ClassicAcount(final CommandInput input) {
        super(0, input.getCurrency(), "classic");
    }

    @Override
    public boolean isTransferPossible(final double amount) {
        return !(this.getBalance() > amount);
    }

    //    public Transaction createTransaction(CommandInput input) {
//        Transaction t = new
//    }
}
