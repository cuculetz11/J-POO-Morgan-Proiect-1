package org.poo.command.transaction;

import org.poo.command.Command;
import org.poo.entities.Bank;
import org.poo.fileio.CommandInput;
import org.poo.services.BankMethods;
import org.poo.services.BankingServices;
import org.poo.services.payment.CardPaymentStrategy;
import org.poo.services.payment.PaymentMethod;
import org.poo.services.payment.PaymentStrategy;

public class PayOnline implements Command {
    @Override
    public void execute(CommandInput input) {
        Bank.getInstance().setCurrentTimestamp(input.getTimestamp());
        BankingServices bankingServices = new BankingServices();

        PaymentStrategy cardPay = new CardPaymentStrategy();
        if(cardPay.checkForErrors(input)) {
            return;
        }
        BankMethods pay = new PaymentMethod(cardPay);

        bankingServices.acceptVisitor(pay);

    }
}
