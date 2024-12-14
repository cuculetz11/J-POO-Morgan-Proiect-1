package org.poo.command.transaction;

import org.poo.command.Command;
import org.poo.entities.Bank;
import org.poo.fileio.CommandInput;
import org.poo.services.BankMethods;
import org.poo.services.payment.CardPaymentStrategy;
import org.poo.services.payment.PaymentMethod;
import org.poo.services.payment.PaymentStrategy;

public class PayOnline implements Command {
    @Override
    public void execute(final CommandInput input) {
        Bank.getInstance().setCurrentTimestamp(input.getTimestamp());

        PaymentStrategy cardPay = new CardPaymentStrategy();
        if (cardPay.checkForErrors(input)) {
            return;
        }
        BankMethods pay = new PaymentMethod(cardPay);

        bankingServices.acceptVisitor(pay);

    }
}
