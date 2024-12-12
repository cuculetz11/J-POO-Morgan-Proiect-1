package org.poo.services;

import org.poo.command.Command;
import org.poo.entities.Bank;
import org.poo.entities.bankAccount.Account;
import org.poo.entities.card.Card;
import org.poo.entities.transaction.Transaction;
import org.poo.fileio.CommandInput;
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
        account.setUser(Bank.getInstance().getUsers().get(userEmail));
        Bank.getInstance().getUsers().get(userEmail).getAccounts().put(accountNameorIBAN, account);
        Bank.getInstance().getAccounts().put(accountNameorIBAN, account);
    }
    public void addTransactionHistory(Transaction transaction, String userEmail) {
        if (Bank.getInstance().getTransactionHistory().get(userEmail) == null) {
             Bank.getInstance().getTransactionHistory().put(userEmail, new ArrayList<>());
        }
        Bank.getInstance().getTransactionHistory().get(userEmail).add(transaction);
    }
    public void addCard(Card card, Account account) {

        card.setAccount(account);
        account.getCards().put(card.getCardNumber(), card);
        Bank.getInstance().getCards().put(card.getCardNumber(), card);
    }
    public void addFounds(String accountNameorIBAN, double amount) {
        Account account = Bank.getInstance().getAccounts().get(accountNameorIBAN);
        if (account == null) {
            //err
            return;
        }
        account.setBalance(account.getBalance() + amount);
    }

    /**
     * Sterge contul si cardurile lui daca balata sa este 0
     * @param emailUser emailul user-ului
     * @param accountNameorIBAN IBAN-ul sau alias-ul contului
     * @return
     */
    public boolean removeAccount(String emailUser, String accountNameorIBAN) {
        Account account = Bank.getInstance().getUsers().get(emailUser).getAccounts().get(accountNameorIBAN);
        if(account.getBalance() != 0) {
            return false;
            }
        for(Card c : account.getCards().values()) {
            Bank.getInstance().getCards().remove(c.getCardNumber());
        }
        account.getCards().clear();
        Bank.getInstance().getUsers().get(emailUser).getAccounts().remove(accountNameorIBAN);
        Bank.getInstance().getAccounts().remove(accountNameorIBAN);
        return true;
    }
    public void deleteCard(String emailUser, String cardNumber) {
        Map<String, Account> userAccounts = Bank.getInstance().getUsers().get(emailUser).getAccounts();
        for (Account account : userAccounts.values()) {
            if (account.getCards().containsKey(cardNumber)) {
                Bank.getInstance().getCardDeletedHistory().put(cardNumber, account.getCards().get(cardNumber));
                account.getCards().remove(cardNumber);
                Bank.getInstance().getCards().remove(cardNumber);
                break;
            }
        }
    }
}
