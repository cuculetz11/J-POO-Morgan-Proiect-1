package org.poo.command;

import org.poo.fileio.CommandInput;
import org.poo.services.AccountServices;
import org.poo.services.BankingServices;

public interface Command {
    BankingServices bankingServices = new BankingServices();
    AccountServices accountServices = new AccountServices();

    void execute(CommandInput input);
}
