package org.poo.command;

import org.poo.fileio.CommandInput;

public interface Command {
    void execute(CommandInput input);
}
