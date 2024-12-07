package org.poo.command;

import org.poo.fileio.CommandInput;
import org.poo.services.AccountServices;

public class SetMinBalance implements Command{
    @Override
    public void execute(CommandInput input) {
        AccountServices accountServices = new AccountServices();
        accountServices.setMinBalance(input.getAccount(), input.getAmount());
    }
}
