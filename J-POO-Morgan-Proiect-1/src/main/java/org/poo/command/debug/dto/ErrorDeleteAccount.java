package org.poo.command.debug.dto;

public class ErrorDeleteAccount implements AccountDeleteInfo {
    private String error;
    private int timestamp;
    public ErrorDeleteAccount(String error, int timestamp) {
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
