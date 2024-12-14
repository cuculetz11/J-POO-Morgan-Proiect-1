package org.poo.services.payment;

import org.poo.fileio.CommandInput;
import org.poo.services.AccountServices;
import org.poo.services.BankingServices;

public interface PaymentStrategy {
    BankingServices bankingServices = new BankingServices();
    AccountServices accountServices = new AccountServices();
    void pay();

    boolean checkForErrors(CommandInput input);
}
