package org.poo.services;

import org.poo.command.Command;
import org.poo.fileio.CommandInput;
import org.poo.fileio.ObjectInput;
import org.poo.services.initialize.Initializator;
import org.poo.utils.CommandManager;

public class InputHandlerServices {
    private ObjectInput input;
    public InputHandlerServices(ObjectInput input) {
        this.input = input;
    }
    public void handle() {
        Initializator initializator = new Initializator(input.getUsers(), input.getExchangeRates());
        initializator.initialize();
        BankingServices bankingServices = new BankingServices();
        for(CommandInput commandInput : input.getCommands() ) {
            bankingServices.performCommand(commandInput);
        }

    }
}
