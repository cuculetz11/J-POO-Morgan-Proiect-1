package org.poo.command.transaction;

import org.poo.command.Command;
import org.poo.entities.bankAccount.Account;
import org.poo.entities.transaction.Transaction;
import org.poo.fileio.CommandInput;
import org.poo.utils.Constants;
import org.poo.utils.factory.AccountFactory;

public class AddAccount implements Command {
    @Override
    public void execute(final CommandInput input) {
        Account account = AccountFactory.getAccount(input);
        String userEmail = input.getEmail();
        bankingServices.addAccount(account, account.getIban(), userEmail);
        Transaction transaction = new Transaction(input.getTimestamp(), Constants.NEW_ACCOUNT_CREATED);
        bankingServices.addTransactionHistory(transaction, userEmail);
        accountServices.addTransactionToHistory(account.getIban(), transaction);
    }
}
