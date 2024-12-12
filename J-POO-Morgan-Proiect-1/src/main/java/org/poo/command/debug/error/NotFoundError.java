package org.poo.command.debug.error;

public class NotFoundError {
    private String description;
    private  int timestamp;
    public NotFoundError(String description, int timestamp) {
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
