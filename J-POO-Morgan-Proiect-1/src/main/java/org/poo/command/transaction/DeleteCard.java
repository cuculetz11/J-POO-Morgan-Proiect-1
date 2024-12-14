package org.poo.command.transaction;

import org.poo.command.Command;
import org.poo.entities.Bank;
import org.poo.entities.bankAccount.Account;
import org.poo.entities.card.Card;
import org.poo.entities.transaction.LifeOfACard;
import org.poo.entities.transaction.Transaction;
import org.poo.fileio.CommandInput;
import org.poo.utils.Constants;
import org.poo.utils.ErrorManager;

public class DeleteCard implements Command {
    @Override
    public void execute(final CommandInput input) {
        if (Bank.getInstance().getCardDeletedHistory().containsKey(input.getCardNumber()))
            return;
        Card card = Bank.getInstance().getCards().get(input.getCardNumber());
        if (card == null) {
            ErrorManager.notFound(input.getCardNumber() + " " + input.getEmail(), input.getCommand(), input.getTimestamp());
            return;
        }

        if (!card.getAccount().getUser().getEmail().equals(input.getEmail()))
            return;

        Account account = Bank.getInstance().getCards().get(input.getCardNumber()).getAccount();
        if (account == null) {
            return;
        }
        String iban = Bank.getInstance().getCards().get(input.getCardNumber()).getAccount().getIban();

        bankingServices.deleteCard(input.getEmail(), input.getCardNumber());
        Transaction cardCreate = new LifeOfACard(Constants.THE_CARD_HAS_BEEN_DESTROYED,
                input.getCardNumber(), input.getEmail(), input.getTimestamp(), iban);

        bankingServices.addTransactionHistory(cardCreate, input.getEmail());
        //Tranzaction de facut
    }
}
