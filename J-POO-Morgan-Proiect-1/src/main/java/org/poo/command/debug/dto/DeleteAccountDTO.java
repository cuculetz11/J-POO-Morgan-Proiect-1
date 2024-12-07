package org.poo.command.debug.dto;

public class DeleteAccountDTO {
    private String success;
    private int timestamp;

    public DeleteAccountDTO(String succes, int timestamp) {
        this.success = succes;
        this.timestamp = timestamp;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
