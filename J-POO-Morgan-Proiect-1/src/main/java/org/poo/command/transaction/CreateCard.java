package org.poo.command.transaction;

import org.poo.command.Command;
import org.poo.entities.Bank;
import org.poo.entities.bankAccount.Account;
import org.poo.entities.card.Card;
import org.poo.entities.transaction.LifeOfACard;
import org.poo.entities.transaction.Transaction;
import org.poo.fileio.CommandInput;
import org.poo.services.AccountServices;
import org.poo.services.BankingServices;
import org.poo.utils.factory.CardFactory;

public class CreateCard implements Command {
    public void execute(CommandInput input) {
        BankingServices bankingServices = new BankingServices();
        AccountServices accountServices = new AccountServices();
        if(Bank.getInstance().getUsers().get(input.getEmail()) == null) {
            return;
        }
        Account account = Bank.getInstance().getUsers().get(input.getEmail()).getAccounts().get(input.getAccount());
        if(account == null) {
            return;
        }

        Card card = CardFactory.getCard(input);
        bankingServices.addCard(card,account);

        Transaction cardCreate = new LifeOfACard("New card created",card.getCardNumber(), input.getEmail(), input.getTimestamp(),card.getAccount().getIBAN());
        bankingServices.addTransactionHistory(cardCreate,input.getEmail());
        accountServices.addTransactionToHistory(input.getAccount(),cardCreate);
    }
}
