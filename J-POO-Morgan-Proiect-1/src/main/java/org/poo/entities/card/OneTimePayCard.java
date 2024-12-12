package org.poo.entities.card;

import org.poo.command.transaction.CreateCard;
import org.poo.command.transaction.DeleteCard;
import org.poo.entities.Bank;
import org.poo.entities.transaction.Transaction;
import org.poo.fileio.CommandInput;
import org.poo.services.BankingServices;

public class OneTimePayCard extends Card {
    public OneTimePayCard() {
        super();
    }

    @Override
    public void pay(double amount) {

        super.getAccount().setBalance(super.getAccount().getBalance() - amount);
        String userEmail = super.getAccount().getUser().getEmail();
        String IBAN = super.getAccount().getIBAN();

        CommandInput inputCreate = new CommandInput();
        inputCreate.setAccount(IBAN);
        inputCreate.setEmail(userEmail);
        inputCreate.setCommand("createOneTimeCard");
        inputCreate.setTimestamp(Bank.getInstance().getCurrentTimestamp());

        CommandInput inputDelete = new CommandInput();
        inputDelete.setCardNumber(super.getCardNumber());
        inputDelete.setEmail(userEmail);
        inputDelete.setTimestamp(Bank.getInstance().getCurrentTimestamp());

        DeleteCard deleteCard = new DeleteCard();
        deleteCard.execute(inputDelete);

        CreateCard createCard = new CreateCard();
        createCard.execute(inputCreate);

    }
}
