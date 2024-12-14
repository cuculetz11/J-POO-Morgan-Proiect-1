package org.poo.command.transaction;

import org.poo.command.Command;
import org.poo.entities.Bank;
import org.poo.entities.bankAccount.Account;
import org.poo.entities.bankAccount.SavingsAccount;
import org.poo.entities.transaction.Transaction;
import org.poo.fileio.CommandInput;
import org.poo.services.BankingServices;
import org.poo.utils.Constants;
import org.poo.utils.ErrorManager;

public class ChangeInterestRate implements Command {
    @Override
    public void execute(final CommandInput input) {
        Account account = Bank.getInstance().getAccounts().get(input.getAccount());
        if (!account.getType().equals(Constants.SAVINGS)) {
            ErrorManager.notFound(Constants.NOT_SAVINGS_ACCOUNT, input.getCommand(), input.getTimestamp());
            return;
        }
        SavingsAccount savingsAccount = (SavingsAccount) account;
        savingsAccount.setInterestRate(input.getInterestRate());
        BankingServices bankingServices = new BankingServices();
        Transaction transaction = new Transaction(input.getTimestamp(), "Interest rate of the account changed to 0.81");
        bankingServices.addTransactionHistory(transaction, account.getUser().getEmail());
    }

}
