package org.poo.command.transaction;

import org.poo.command.Command;
import org.poo.entities.bankAccount.Account;
import org.poo.entities.transaction.AccountCreate;
import org.poo.entities.transaction.Transaction;
import org.poo.fileio.CommandInput;
import org.poo.services.AccountServices;
import org.poo.services.BankingServices;
import org.poo.utils.factory.AccountFactory;

public class AddAccount implements Command {
    public void execute(CommandInput input) {
        BankingServices bankingServices = new BankingServices();
        AccountServices accountServices = new AccountServices();
        Account account = AccountFactory.getAccount(input);
        String userEmail = input.getEmail();
        bankingServices.addAccount(account, account.getIBAN(), userEmail);
        Transaction transaction = new AccountCreate(input.getTimestamp());
        bankingServices.addTransactionHistory(transaction, userEmail);
        accountServices.addTransactionToHistory(account.getIBAN(), transaction);
    }
}
