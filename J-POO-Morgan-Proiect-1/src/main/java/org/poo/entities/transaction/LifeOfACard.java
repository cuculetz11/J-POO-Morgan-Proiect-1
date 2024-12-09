package org.poo.entities.transaction;

public class LifeOfACard extends Transaction {
    private String card;
    private String cardHolder;
    private String account;
    public LifeOfACard(String description, String card, String holder, int timestamp, String account) {
        super(timestamp,description);
        this.card = card;
        this.cardHolder = holder;
        this.account = account;
    }

    public String getCard() {
        return card;
    }

    public String getCardHolder() {
        return cardHolder;
    }
    public String getAccount() {
        return account;
    }
}
