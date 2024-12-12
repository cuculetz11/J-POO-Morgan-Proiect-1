package org.poo.command.transaction;

import org.poo.command.Command;
import org.poo.entities.Bank;
import org.poo.entities.bankAccount.Account;
import org.poo.entities.bankAccount.SavingsAccount;
import org.poo.entities.transaction.Transaction;
import org.poo.fileio.CommandInput;
import org.poo.services.AccountServices;
import org.poo.services.BankingServices;
import org.poo.utils.ErrorManager;

public class ChangeInterestRate implements Command {
    @Override
    public void execute(CommandInput input) {
        Account account = Bank.getInstance().getAccounts().get(input.getAccount());
        if(account.getType() != "savings") {
            ErrorManager.notFound("This is not a savings account",input.getCommand(), input.getTimestamp());
            return;
        }
        SavingsAccount savingsAccount = (SavingsAccount) account;
        savingsAccount.setInterestRate(input.getInterestRate());
        BankingServices bankingServices = new BankingServices();
        Transaction transaction = new Transaction(input.getTimestamp(), "Interest rate of the account changed to 0.81");
        bankingServices.addTransactionHistory(transaction,account.getUser().getEmail());
    }

}
