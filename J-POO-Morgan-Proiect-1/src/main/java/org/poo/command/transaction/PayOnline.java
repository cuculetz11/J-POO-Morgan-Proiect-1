package org.poo.command.transaction;

import org.poo.command.Command;
import org.poo.entities.Bank;
import org.poo.fileio.CommandInput;
import org.poo.services.BankMethods;
import org.poo.services.payment.CardPayment;
import org.poo.services.payment.PaymentMethod;
import org.poo.services.payment.PaymentStrategy;

public class PayOnline implements Command {
    @Override
    public void execute(CommandInput input) {
        PaymentStrategy cardPay = new CardPayment(Bank.getInstance().getCards().get(input.getCardNumber()));
        BankMethods pay = new PaymentMethod(cardPay);
        

    }
}
