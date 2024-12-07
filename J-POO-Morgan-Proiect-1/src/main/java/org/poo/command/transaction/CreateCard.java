package org.poo.command.transaction;

import org.poo.command.Command;
import org.poo.entities.card.Card;
import org.poo.entities.transaction.CardCreate;
import org.poo.entities.transaction.Transaction;
import org.poo.fileio.CommandInput;
import org.poo.services.BankingServices;
import org.poo.utils.factory.CardFactory;

public class CreateCard implements Command {
    public void execute(CommandInput input) {
        BankingServices bankingServices = new BankingServices();
        Card card = CardFactory.getCard(input);
        bankingServices.addCard(card,input.getEmail(),input.getAccount());
        Transaction cardCreate = new CardCreate(input.getCardNumber(), input.getEmail(), input.getTimestamp());
        bankingServices.addTransactionHistory(cardCreate,input.getEmail());
    }
}
