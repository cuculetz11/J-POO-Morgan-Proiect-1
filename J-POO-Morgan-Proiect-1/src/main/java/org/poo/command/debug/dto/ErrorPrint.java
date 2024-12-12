package org.poo.command.debug.dto;

public class ErrorPrint implements AccountDeleteInfo {
    private String error;
    private int timestamp;
    public ErrorPrint(String error, int timestamp) {
        this.error = error;
        this.timestamp = timestamp;
    }
    public String getError() {
        return error;
    }
    public int getTimestamp() {
        return timestamp;
    }
}
