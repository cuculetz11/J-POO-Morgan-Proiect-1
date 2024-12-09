package org.poo.command.debug.dto;

public class CardActionsInfo {
    private String description;
    private  int timestamp;
    public CardActionsInfo(String description, int timestamp) {
        this.description = description;
        this.timestamp = timestamp;
    }
    public String getDescription() {
        return description;
    }
    public int getTimestamp() {
        return timestamp;
    }

}
