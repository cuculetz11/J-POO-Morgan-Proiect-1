package org.poo.utils;

import org.poo.command.Command;
import org.poo.command.SetMinBalance;
import org.poo.command.debug.PrintUsers;
import org.poo.command.transaction.*;

/*
Aceasta calsa e un fel de factory pentu comenzi
aceeasti aborfare am avut o si in tema trevute mi se pare o abordare eleganat si usoara
 */
public class CommandManager {
    private CommandManager(){}
    public static Command getConcreteCommand(String commandName) {
        switch(commandName){
            case "printUsers":
                return new PrintUsers();
            case "addAccount":
                return new AddAccount();
            case "createCard", "createOneTimeCard":
                return new CreateCard();
            case "addFunds":
                return new AddFounds();
            case "deleteAccount":
                return new DeleteAccount();
            case "deleteCard":
                return new DeleteCard();
            case "setMinimumBalance":
                return new SetMinBalance();
            case "payOnline":
                return new PayOnline();


        }
        throw new IllegalArgumentException("Command not found");
    }

}
