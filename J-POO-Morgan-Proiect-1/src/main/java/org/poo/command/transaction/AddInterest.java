package org.poo.command.transaction;

import org.poo.command.Command;
import org.poo.entities.Bank;
import org.poo.entities.bankAccount.Account;
import org.poo.entities.bankAccount.SavingsAccount;
import org.poo.fileio.CommandInput;
import org.poo.utils.Constants;
import org.poo.utils.ErrorManager;

public class AddInterest implements Command {
    @Override
    public void execute(final CommandInput input) {
        Account account = Bank.getInstance().getAccounts().get(input.getAccount());
        if (!account.getType().equals(Constants.SAVINGS)) {
            ErrorManager.notFound(Constants.NOT_SAVINGS_ACCOUNT, input.getCommand(), input.getTimestamp());
            return;
        }
        SavingsAccount savingsAccount = (SavingsAccount) account;
        savingsAccount.setBalance(savingsAccount.getBalance() + savingsAccount.getBalance() * savingsAccount.getInterestRate());
    }
}
