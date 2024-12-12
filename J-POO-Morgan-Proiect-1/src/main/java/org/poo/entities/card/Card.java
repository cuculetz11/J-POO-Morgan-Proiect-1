package org.poo.entities.card;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.poo.entities.bankAccount.Account;
import org.poo.entities.transaction.Transaction;
import org.poo.utils.Utils;

public abstract class Card {
    private String cardNumber;
    private String status;
    @JsonIgnore
    private Account account;

    public abstract void pay(double amount);
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
