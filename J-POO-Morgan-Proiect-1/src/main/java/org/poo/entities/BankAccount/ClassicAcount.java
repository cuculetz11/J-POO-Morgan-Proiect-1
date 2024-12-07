package org.poo.entities.BankAccount;

import org.poo.entities.transaction.Transaction;
import org.poo.fileio.CommandInput;
import org.poo.utils.Utils;

public class ClassicAcount extends Account {
    public ClassicAcount(CommandInput input) {
        super(0, input.getCurrency(), "classic");
    }
//    public Transaction createTransaction(CommandInput input) {
//        Transaction t = new
//    }
}
