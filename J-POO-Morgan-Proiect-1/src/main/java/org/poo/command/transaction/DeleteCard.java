package org.poo.command.transaction;

import org.poo.command.Command;
import org.poo.entities.Bank;
import org.poo.entities.transaction.LifeOfACard;
import org.poo.entities.transaction.Transaction;
import org.poo.fileio.CommandInput;
import org.poo.services.BankingServices;

public class DeleteCard implements Command {
    @Override
    public void execute(CommandInput input) {
        String IBAN = Bank.getInstance().getCards().get(input.getCardNumber()).getAccount().getIBAN();
        BankingServices bankingServices = new BankingServices();
        bankingServices.deleteCard(input.getEmail(),input.getCardNumber());
        Transaction cardCreate = new LifeOfACard("The card has been destroyed",input.getCardNumber(), input.getEmail(), input.getTimestamp(),IBAN);
        bankingServices.addTransactionHistory(cardCreate,input.getEmail());
        //Tranzaction de facut
    }
}
