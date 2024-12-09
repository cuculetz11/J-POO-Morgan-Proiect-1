package org.poo.command.transaction;

import org.poo.command.Command;
import org.poo.fileio.CommandInput;
import org.poo.services.BankMethods;
import org.poo.services.BankingServices;
import org.poo.services.payment.BankTransferStrategy;
import org.poo.services.payment.PaymentMethod;
import org.poo.services.payment.PaymentStrategy;

public class SendMoney implements Command {
    @Override
    public void execute(CommandInput input) {
        BankingServices bankingServices = new BankingServices();
        PaymentStrategy bankTransfer = new BankTransferStrategy();
        if(bankTransfer.checkForErrors(input))
            return;
        BankMethods transfer = new PaymentMethod(bankTransfer);
        bankingServices.acceptVisitor(transfer);
    }
}
