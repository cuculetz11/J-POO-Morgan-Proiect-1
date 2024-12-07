package org.poo.services;

import org.poo.command.Command;
import org.poo.entities.Bank;
import org.poo.entities.BankAccount.Account;
import org.poo.entities.card.Card;
import org.poo.entities.transaction.Transaction;
import org.poo.fileio.CommandInput;
import org.poo.fileio.ObjectInput;
import org.poo.utils.CommandManager;

import java.util.ArrayList;
import java.util.Map;

public class BankingServices {
    public void acceptVisitor(BankMethods visitor) {
        visitor.visit(Bank.getInstance());
    }
    public void performCommand(CommandInput input) {
        Command c = CommandManager.getConcreteCommand(input.getCommand());
        c.execute(input);
    }
    public void addAccount(Account account, String accountNameorIBAN, String userEmail) {
        Bank.getInstance().getUsers().get(userEmail).getAccounts().put(accountNameorIBAN, account);
        Bank.getInstance().getAccounts().put(accountNameorIBAN, account);
    }
    public void addTransactionHistory(Transaction transaction, String userEmail) {
        ArrayList<Transaction> usersTransaction = Bank.getInstance().getTransactionHistory().get(userEmail);
        if (usersTransaction == null) {
            usersTransaction = new ArrayList<>();
        }
        usersTransaction.add(transaction);
    }
    public void addCard(Card card, String userEmail, String accountNameorIBAN) {
        Account account = Bank.getInstance().getUsers().get(userEmail).getAccounts().get(accountNameorIBAN);
        if (account == null) {
            //err
            return;
        }
        account.getCards().put(card.getCardNumber(), card);
    }
    public void addFounds(String accountNameorIBAN, double amount) {
        Account account = Bank.getInstance().getAccounts().get(accountNameorIBAN);
        if (account == null) {
            //err
        }
        account.setBalance(account.getBalance() + amount);
    }
    public boolean removeAccount(String emailUser, String accountNameorIBAN) {
        Bank.getInstance().getUsers().get(emailUser).getAccounts().remove(accountNameorIBAN);
        Bank.getInstance().getAccounts().remove(accountNameorIBAN);
        return true;
    }
    public void deleteCard(String emailUser, String cardNumber) {
        Map<String, Account> userAccounts = Bank.getInstance().getUsers().get(emailUser).getAccounts();
        for (Account account : userAccounts.values()) {
            if (account.getCards().containsKey(cardNumber)) {
                account.getCards().remove(cardNumber);
            } else {
                //mesaj eroare
            }
        }
    }
}
