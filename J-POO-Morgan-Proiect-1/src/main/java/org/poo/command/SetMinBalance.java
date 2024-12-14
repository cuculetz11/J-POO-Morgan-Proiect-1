package org.poo.command;

import org.poo.fileio.CommandInput;

public class SetMinBalance implements Command {
    @Override
    public void execute(final CommandInput input) {
        accountServices.setMinBalance(input.getAccount(), input.getAmount());
    }
}
