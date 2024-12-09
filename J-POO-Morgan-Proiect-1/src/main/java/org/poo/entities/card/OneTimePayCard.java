package org.poo.entities.card;

import org.poo.fileio.CommandInput;
import org.poo.services.BankingServices;

public class OneTimePayCard extends Card {
    public OneTimePayCard() {
        super();
    }

    @Override
    public void pay(double amount) {

        super.getAccount().setBalance(super.getAccount().getBalance() - amount);
        BankingServices bankingServices = new BankingServices();
        bankingServices.deleteCard(super.getAccount().getUser().getEmail(), super.getCardNumber());

    }
}
