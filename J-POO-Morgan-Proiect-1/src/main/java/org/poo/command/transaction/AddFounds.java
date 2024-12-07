package org.poo.command.transaction;

import org.poo.command.Command;
import org.poo.fileio.CommandInput;
import org.poo.services.BankingServices;

public class AddFounds implements Command {
    public void execute(CommandInput input) {
        BankingServices bankingServices = new BankingServices();
        bankingServices.addFounds(input.getAccount(), input.getAmount());
    }
}
