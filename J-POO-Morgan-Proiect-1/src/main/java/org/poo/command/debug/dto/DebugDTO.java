package org.poo.command.debug.dto;

import java.util.ArrayList;

public class DebugDTO<T> {
    private String command;
    private ArrayList<T> output;
    private int timestamp;
    public DebugDTO(String command, ArrayList<T> output, int timestamp) {
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

    public ArrayList<T> getOutput() {
        return output;
    }

    public void setOutput(ArrayList<T> output) {
        this.output = output;
    }
}
