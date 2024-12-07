package org.poo.command.transaction;

import org.poo.command.Command;
import org.poo.command.debug.dto.DebugActionsDTO;
import org.poo.command.debug.dto.DebugDTO;
import org.poo.command.debug.dto.DeleteAccountDTO;
import org.poo.fileio.CommandInput;
import org.poo.services.BankingServices;
import org.poo.utils.JsonOutManager;

import java.util.ArrayList;

public class DeleteAccount implements Command {
    public void execute(CommandInput input) {
        BankingServices bankingServices = new BankingServices();
        DeleteAccountDTO data = null;

        if(bankingServices.removeAccount(input.getEmail(), input.getAccount())) {
            data = new DeleteAccountDTO("Account deleted", input.getTimestamp());
        }
        DebugActionsDTO wasAccountDeleted = new DebugActionsDTO<DeleteAccountDTO>(input.getCommand(),data, input.getTimestamp());
        JsonOutManager.getInstance().addToOutput(wasAccountDeleted);
        //transaction
    }
}
