package org.poo.services.initialize;

import org.poo.entities.Bank;
import org.poo.entities.user.User;
import org.poo.fileio.UserInput;
import org.poo.services.BankMethods;

public class UserInitialize implements BankMethods {
    private final UserInput[] userInputs;

    public UserInitialize(final UserInput[] userInputs) {
        this.userInputs = userInputs;
    }

    public void visit(final Bank bank) {
        for (UserInput userInput : userInputs) {
            User user = new User(userInput);
            bank.getUsers().put(user.getEmail(), user);
        }
    }
}
