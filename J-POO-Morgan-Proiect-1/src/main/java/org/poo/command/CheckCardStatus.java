package org.poo.command;

import org.poo.command.debug.dto.CardActionsInfo;
import org.poo.command.debug.dto.DebugActionsDTO;
import org.poo.entities.Bank;
import org.poo.entities.card.Card;
import org.poo.entities.transaction.Transaction;
import org.poo.fileio.CommandInput;
import org.poo.services.BankingServices;
import org.poo.utils.JsonOutManager;

public class CheckCardStatus implements Command{
    @Override
    public void execute(CommandInput input) {
        BankingServices bankingServices = new BankingServices();
        Card card = Bank.getInstance().getCards().get(input.getCardNumber());
        if (card == null) {
            CardActionsInfo info = new CardActionsInfo("Card not found", input.getTimestamp());
            DebugActionsDTO<CardActionsInfo> cardNotFound = new DebugActionsDTO<>(input.getCommand(),info,input.getTimestamp());
            JsonOutManager.getInstance().addToOutput(cardNotFound);
            return;
        }
        if(card.getStatus().equals("frozen") && card.getAccount().verifyBalance()){
            return;
        }
        if(card.getAccount().verifyBalance()) {
            card.setStatus("frozen");
            //CardActionsInfo info = new CardActionsInfo("You have reached the minimum amount of funds, the card will be frozen", input.getTimestamp());
            Transaction transaction = new Transaction(input.getTimestamp(), "You have reached the minimum amount of funds, the card will be frozen");
            bankingServices.addTransactionHistory(transaction,card.getAccount().getUser().getEmail());
        }

    }
}
