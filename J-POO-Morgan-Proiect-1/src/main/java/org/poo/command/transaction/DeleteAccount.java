package org.poo.command.transaction;

import org.poo.command.Command;
import org.poo.command.debug.dto.*;
import org.poo.entities.Bank;
import org.poo.entities.transaction.Transaction;
import org.poo.fileio.CommandInput;
import org.poo.services.BankingServices;
import org.poo.utils.JsonOutManager;

public class DeleteAccount implements Command {
    public void execute(CommandInput input) {
        BankingServices bankingServices = new BankingServices();
        AccountDeleteInfo data = null;

        if(bankingServices.removeAccount(input.getEmail(), input.getAccount())) {
            data = new DeleteAccountDTO("Account deleted", input.getTimestamp());
        } else {
            Transaction transaction = new Transaction(input.getTimestamp(),"Account couldn't be deleted - there are funds remaining");
            bankingServices.addTransactionHistory(transaction,input.getEmail());
            data = new ErrorPrint("Account couldn't be deleted - see org.poo.transactions for details", input.getTimestamp());
        }
        DebugActionsDTO<AccountDeleteInfo> wasAccountDeleted = new DebugActionsDTO<>(input.getCommand(), data, input.getTimestamp());
        JsonOutManager.getInstance().addToOutput(wasAccountDeleted);
        //transaction
    }
}
