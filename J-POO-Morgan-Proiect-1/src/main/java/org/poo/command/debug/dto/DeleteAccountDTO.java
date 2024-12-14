package org.poo.command.debug.dto;

import lombok.Getter;

@Getter
public class DeleteAccountDTO implements AccountDeleteInfo {
    private final String success;
    private int timestamp;

    public DeleteAccountDTO(final String succes, final int timestamp) {
        this.success = succes;
        this.timestamp = timestamp;
    }

    public void setTimestamp( final int timestamp) {
        this.timestamp = timestamp;
    }
}
