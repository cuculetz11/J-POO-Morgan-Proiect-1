package org.poo.command.transaction;

import org.poo.command.Command;
import org.poo.entities.Bank;
import org.poo.entities.bankAccount.Account;
import org.poo.entities.bankAccount.SavingsAccount;
import org.poo.fileio.CommandInput;
import org.poo.services.AccountServices;
import org.poo.utils.ErrorManager;

public class AddInterest implements Command {
    @Override
    public void execute(CommandInput input) {
        Account account = Bank.getInstance().getAccounts().get(input.getAccount());
        if(account.getType() != "savings") {
            ErrorManager.notFound("This is not a savings account",input.getCommand(), input.getTimestamp());
            return;
        }
        SavingsAccount savingsAccount = (SavingsAccount) account;
        savingsAccount.setBalance(savingsAccount.getBalance() + savingsAccount.getBalance() * savingsAccount.getInterestRate());
    }
}
