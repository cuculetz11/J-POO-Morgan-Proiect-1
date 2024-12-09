package org.poo.command.debug;

import org.poo.command.Command;
import org.poo.command.debug.dto.DebugDTO;
import org.poo.entities.Bank;
import org.poo.entities.transaction.Transaction;
import org.poo.fileio.CommandInput;
import org.poo.utils.JsonOutManager;

import java.util.ArrayList;

public class PrintTransaction implements Command {
    @Override
    public void execute(CommandInput input) {
        ArrayList<Transaction> transactions = Bank.getInstance().getTransactionHistory().get(input.getEmail());
        DebugDTO<Transaction> printTransaction = new DebugDTO<Transaction>(input.getCommand(),transactions, input.getTimestamp());
        JsonOutManager.getInstance().addToOutput(printTransaction);
    }
}
