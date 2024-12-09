package org.poo.command.transaction;

import org.poo.command.Command;
import org.poo.command.debug.dto.CardActionsInfo;
import org.poo.command.debug.dto.DebugActionsDTO;
import org.poo.entities.Bank;
import org.poo.entities.CurrencyPair;
import org.poo.entities.card.Card;
import org.poo.entities.transaction.CardPayment;
import org.poo.entities.transaction.Transaction;
import org.poo.fileio.CommandInput;
import org.poo.services.AccountServices;
import org.poo.services.BankMethods;
import org.poo.services.BankingServices;
import org.poo.services.payment.CardPaymentStrategy;
import org.poo.services.payment.PaymentMethod;
import org.poo.services.payment.PaymentStrategy;
import org.poo.utils.JsonOutManager;

public class PayOnline implements Command {
    @Override
    public void execute(CommandInput input) {
        BankingServices bankingServices = new BankingServices();
        PaymentStrategy cardPay = new CardPaymentStrategy();
        if(cardPay.checkForErrors(input)) {
            return;
        }
        BankMethods pay = new PaymentMethod(cardPay);

        bankingServices.acceptVisitor(pay);

    }
}
