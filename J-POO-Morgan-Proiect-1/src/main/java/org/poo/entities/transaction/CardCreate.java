package org.poo.entities.transaction;

public class CardCreate extends Transaction {
    private String card;
    private String holder;
    public CardCreate(String card, String holder, int timestamp) {
        super(timestamp,"New card created");
        this.card = card;
        this.holder = holder;
    }
}
