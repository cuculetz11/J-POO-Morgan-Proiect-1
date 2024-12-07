package org.poo.entities.card;

import org.poo.entities.BankAccount.Account;
import org.poo.utils.Utils;

public abstract class Card {
    private String cardNumber;
    private String status;
    //private Account account;
    public Card() {
        this.cardNumber = Utils.generateCardNumber();
        this.status = "active";
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
