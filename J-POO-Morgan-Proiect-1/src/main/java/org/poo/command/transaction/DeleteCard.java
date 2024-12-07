package org.poo.command.transaction;

import org.poo.command.Command;
import org.poo.fileio.CommandInput;
import org.poo.services.BankingServices;

public class DeleteCard implements Command {
    @Override
    public void execute(CommandInput input) {
        BankingServices bankingServices = new BankingServices();
        bankingServices.deleteCard(input.getEmail(),input.getCardNumber());
        //Tranzaction de facut
    }
}
