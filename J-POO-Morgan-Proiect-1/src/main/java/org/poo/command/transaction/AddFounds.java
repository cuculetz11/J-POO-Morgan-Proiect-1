package org.poo.command.transaction;

import org.poo.command.Command;
import org.poo.fileio.CommandInput;

public class AddFounds implements Command {
    public void execute(final CommandInput input) {
        bankingServices.addFounds(input.getAccount(), input.getAmount());
    }
}
