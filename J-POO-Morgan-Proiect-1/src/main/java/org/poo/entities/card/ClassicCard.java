package org.poo.entities.card;

import org.poo.fileio.CommandInput;

public class ClassicCard extends Card {
    public ClassicCard() {
        super();
    }
    public void pay(double amount) {

            super.getAccount().setBalance(super.getAccount().getBalance() - amount);
    }
}
