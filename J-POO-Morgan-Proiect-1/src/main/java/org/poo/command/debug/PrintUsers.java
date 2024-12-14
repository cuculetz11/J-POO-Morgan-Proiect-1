package org.poo.command.debug;

import org.poo.command.Command;
import org.poo.command.debug.dto.DebugDTO;
import org.poo.entities.Bank;
import org.poo.entities.user.User;
import org.poo.fileio.CommandInput;
import org.poo.utils.JsonOutManager;

import java.util.ArrayList;

public class PrintUsers implements Command {
    @Override
    public void execute(final CommandInput input) {
        ArrayList<User> users = new ArrayList<>(Bank.getInstance().getUsers().values());
        DebugDTO<User> printUsers = new DebugDTO<User>(input.getCommand(), users, input.getTimestamp());
        JsonOutManager.getInstance().addToOutput(printUsers);
    }
}
