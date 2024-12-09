package org.poo.utils.factory;

import org.poo.entities.bankAccount.Account;
import org.poo.entities.bankAccount.ClassicAcount;
import org.poo.entities.bankAccount.SavingsAccount;
import org.poo.fileio.CommandInput;

public class AccountFactory {
    public static Account getAccount(CommandInput input) {
        switch (input.getAccountType()) {
            case "classic":
                return new ClassicAcount(input);
            case "savings":
                return new SavingsAccount(input);
            default:
                return null;
        }
    }
}
