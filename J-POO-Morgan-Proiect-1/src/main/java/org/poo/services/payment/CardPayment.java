package org.poo.services.payment;

import org.poo.entities.card.Card;

public class CardPayment implements PaymentStrategy{
    private Card card;
    public CardPayment(Card card) {
        this.card = card;
    }
    @Override
    public void pay(double amount) {
        card.getAccount().setBalance(card.getAccount().getBalance() - amount);
        //card.getAccount().verifyBalance();
    }
}
