package org.poo.services.payment;

import org.poo.entities.Bank;
import org.poo.entities.CurrencyPair;
import org.poo.services.AccountServices;
import org.poo.services.BankMethods;

public class PaymentMethod implements BankMethods {
    PaymentStrategy paymentStrategy;
    public PaymentMethod(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
    @Override
    public void visit(Bank bank) {
        paymentStrategy.pay();
    }
}
