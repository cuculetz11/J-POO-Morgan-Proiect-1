package org.poo.command.transaction;

import org.poo.command.Command;
import org.poo.command.debug.error.NotFoundError;
import org.poo.command.debug.dto.DebugActionsDTO;
import org.poo.entities.Bank;
import org.poo.entities.bankAccount.Account;
import org.poo.entities.card.Card;
import org.poo.entities.transaction.LifeOfACard;
import org.poo.entities.transaction.Transaction;
import org.poo.fileio.CommandInput;
import org.poo.services.BankingServices;
import org.poo.utils.ErrorManager;
import org.poo.utils.JsonOutManager;

public class DeleteCard implements Command {
    @Override
    public void execute(CommandInput input) {
        if(Bank.getInstance().getCardDeletedHistory().containsKey(input.getCardNumber()))
            return;
        Card card = Bank.getInstance().getCards().get(input.getCardNumber());
        if (card == null) {
            ErrorManager.notFound(input.getCardNumber() + " " + input.getEmail(),input.getCommand(), input.getTimestamp());
            return;
        }

        if(!card.getAccount().getUser().getEmail().equals(input.getEmail()))
            return;

        Account account = Bank.getInstance().getCards().get(input.getCardNumber()).getAccount();
        if(account == null) {
            return;
        }
        String IBAN = Bank.getInstance().getCards().get(input.getCardNumber()).getAccount().getIBAN();
        BankingServices bankingServices = new BankingServices();
        bankingServices.deleteCard(input.getEmail(),input.getCardNumber());
        Transaction cardCreate = new LifeOfACard("The card has been destroyed",input.getCardNumber(), input.getEmail(), input.getTimestamp(),IBAN);
        bankingServices.addTransactionHistory(cardCreate,input.getEmail());
        //Tranzaction de facut
    }
}
