package org.poo.utils.factory;

import org.poo.entities.bankAccount.Account;
import org.poo.entities.bankAccount.ClassicAcount;
import org.poo.entities.bankAccount.SavingsAccount;
import org.poo.fileio.CommandInput;

public class AccountFactory {
    public static Account getAccount(CommandInput input) {
        return switch (input.getAccountType()) {
            case "classic" -> new ClassicAcount(input);
            case "savings" -> new SavingsAccount(input);
            default -> null;
        };
    }
}
