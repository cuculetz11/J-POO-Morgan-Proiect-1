package org.poo.command.transaction;

import org.poo.command.Command;
import org.poo.entities.Bank;
import org.poo.entities.bankAccount.Account;
import org.poo.entities.bankAccount.SavingsAccount;
import org.poo.fileio.CommandInput;
import org.poo.services.AccountServices;

public class ChangeInterestRate implements Command {
    @Override
    public void execute(CommandInput input) {
        Account account = Bank.getInstance().getAccounts().get(input.getAccount());
        if(account.getType() != "savings") {
            //err
            return;
        }
        SavingsAccount savingsAccount = (SavingsAccount) account;
        savingsAccount.setInterestRate(input.getInterestRate());
    }
}
