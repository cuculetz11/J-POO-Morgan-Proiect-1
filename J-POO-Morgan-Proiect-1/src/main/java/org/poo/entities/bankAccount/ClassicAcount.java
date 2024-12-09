package org.poo.entities.bankAccount;

import org.poo.fileio.CommandInput;

public class ClassicAcount extends Account {
    public ClassicAcount(CommandInput input) {
        super(0, input.getCurrency(), "classic");
    }

    @Override
    public boolean isTransferPossible(double amount) {
        if(this.getBalance() > amount) {
            return true;
        }
        return false;
    }

    //    public Transaction createTransaction(CommandInput input) {
//        Transaction t = new
//    }
}
