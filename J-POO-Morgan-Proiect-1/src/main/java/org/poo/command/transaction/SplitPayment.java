package org.poo.command.transaction;

import org.poo.command.Command;
import org.poo.fileio.CommandInput;
import org.poo.services.BankMethods;
import org.poo.services.BankingServices;
import org.poo.services.payment.PaymentMethod;
import org.poo.services.payment.PaymentStrategy;
import org.poo.services.payment.SplitPaymentStrategy;

public class SplitPayment implements Command {
    @Override
    public void execute(final CommandInput input) {
        PaymentStrategy split = new SplitPaymentStrategy();
        if (split.checkForErrors(input)) {
            return;
        }
        BankMethods pay = new PaymentMethod(split);
        bankingServices.acceptVisitor(pay);

    }
}
