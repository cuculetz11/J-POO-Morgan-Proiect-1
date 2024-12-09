package org.poo.services.payment;

import org.poo.fileio.CommandInput;

public interface PaymentStrategy {
    public void pay();
    public boolean checkForErrors(CommandInput input);
}
