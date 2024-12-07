package org.poo.command.debug.dto;

import java.util.ArrayList;

public class DebugActionsDTO<T> {
    private String command;
    private T output;
    private int timestamp;

    public DebugActionsDTO(String command, T output, int timestamp) {
        this.command = command;
        this.output = output;
        this.timestamp = timestamp;
    }
    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public T getOutput() {
        return output;
    }

    public void setOutput(T output) {
        this.output = output;
    }
}
