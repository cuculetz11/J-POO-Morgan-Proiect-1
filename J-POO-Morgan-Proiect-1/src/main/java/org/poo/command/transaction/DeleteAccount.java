package org.poo.command.transaction;

import org.poo.command.Command;
import org.poo.command.debug.dto.AccountDeleteInfo;
import org.poo.command.debug.dto.DebugActionsDTO;
import org.poo.command.debug.dto.DeleteAccountDTO;
import org.poo.command.debug.dto.ErrorPrint;
import org.poo.entities.transaction.Transaction;
import org.poo.fileio.CommandInput;
import org.poo.utils.Constants;
import org.poo.utils.JsonOutManager;

public class DeleteAccount implements Command {
    public void execute(final CommandInput input) {
        AccountDeleteInfo data = null;

        if (bankingServices.removeAccount(input.getEmail(), input.getAccount())) {
            data = new DeleteAccountDTO(Constants.ACCOUNT_DELETED, input.getTimestamp());
        } else {
            Transaction transaction = new Transaction(input.getTimestamp(),
                    Constants.ACCOUNT_CANT_BE_DELETED_FUNDS);
            bankingServices.addTransactionHistory(transaction, input.getEmail());
            data = new ErrorPrint(Constants.ACCOUNT_CANT_BE_DELETED, input.getTimestamp());
        }
        DebugActionsDTO<AccountDeleteInfo> wasAccountDeleted =
                new DebugActionsDTO<>(input.getCommand(), data, input.getTimestamp());
        JsonOutManager.getInstance().addToOutput(wasAccountDeleted);
    }
}
