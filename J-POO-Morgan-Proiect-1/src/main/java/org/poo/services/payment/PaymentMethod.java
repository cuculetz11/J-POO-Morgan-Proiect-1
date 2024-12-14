package org.poo.services.payment;

import org.poo.entities.Bank;
import org.poo.services.BankMethods;

public class PaymentMethod implements BankMethods {
    private final PaymentStrategy paymentStrategy;

    public PaymentMethod(final PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    @Override
    public void visit(final Bank bank) {
        paymentStrategy.pay();
    }
}
