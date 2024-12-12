package org.poo.command.report;

import org.poo.command.Command;
import org.poo.command.debug.dto.AccountCommerciantsDTO;
import org.poo.command.debug.dto.DebugActionsDTO;
import org.poo.command.debug.dto.DeleteAccountDTO;
import org.poo.command.debug.error.ErrorCommand;
import org.poo.entities.Bank;
import org.poo.entities.Merchant;
import org.poo.entities.bankAccount.Account;
import org.poo.entities.transaction.CardPayment;
import org.poo.entities.transaction.Transaction;
import org.poo.fileio.CommandInput;
import org.poo.utils.ErrorManager;
import org.poo.utils.JsonOutManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

public class SpendingReport implements Command {
    @Override
    public void execute(CommandInput input) {
        Account account = Bank.getInstance().getAccounts().get(input.getAccount());
        if(account == null) {
            ErrorManager.notFound("Account not found",input.getCommand(), input.getTimestamp());
            return;
        }

        if(account.getType().equals("savings")) {
            DebugActionsDTO<ErrorCommand> error = new DebugActionsDTO<>(input.getCommand(), new ErrorCommand("This kind of report is not supported for a saving account"), input.getTimestamp());
            JsonOutManager.getInstance().addToOutput(error);
            return;
        }
        ArrayList<Transaction> transactions = new ArrayList<>();
        LinkedHashMap<String,Merchant> merchants = new LinkedHashMap<>();


        for(Transaction transaction : account.getTransactionsHistory()) {
            if(transaction.getTimestamp() > input.getEndTimestamp()) {
                break;
            }
            if(transaction.getTimestamp() >= input.getStartTimestamp() && transaction.getDescription().equals("Card payment")) {
                transactions.add(transaction);
                CardPayment cardPayment = (CardPayment) transaction;
                if(!merchants.containsKey(cardPayment.getCommerciant())) {
                    Merchant merchant = new Merchant(cardPayment.getCommerciant(), cardPayment.getAmount());
                    merchants.put(cardPayment.getCommerciant(), merchant);
            } else {
                    merchants.get(cardPayment.getCommerciant()).setTotal(merchants.get(cardPayment.getCommerciant()).getTotal() + cardPayment.getAmount());
                }
            }
        }
        ArrayList<Merchant> merchantsList = new ArrayList<>(merchants.values());
        merchantsList.sort((c2,c1) -> c2.getCommerciant().compareTo(c1.getCommerciant()) );
        AccountCommerciantsDTO accountCommerciantsDTO = new AccountCommerciantsDTO(input.getAccount(),account.getBalance(),account.getCurrency(),transactions,merchantsList);
        DebugActionsDTO<AccountCommerciantsDTO> spendingReports = new DebugActionsDTO<>(input.getCommand(),accountCommerciantsDTO, input.getTimestamp());
        JsonOutManager.getInstance().addToOutput(spendingReports);

    }
}
