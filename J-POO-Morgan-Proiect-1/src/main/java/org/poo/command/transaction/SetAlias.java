package org.poo.command.transaction;

import org.poo.command.Command;
import org.poo.fileio.CommandInput;
import org.poo.services.AccountServices;

public class SetAlias implements Command {
    @Override
    public void execute(final CommandInput input) {
        accountServices.setAlias(input.getEmail(), input.getAccount(), input.getAlias());
    }
}
