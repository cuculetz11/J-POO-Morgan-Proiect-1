package org.poo.utils;

import org.poo.command.CheckCardStatus;
import org.poo.command.Command;
import org.poo.command.SetMinBalance;
import org.poo.command.debug.PrintTransaction;
import org.poo.command.debug.PrintUsers;
import org.poo.command.transaction.*;
import org.poo.fileio.UserInput;

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
            case "sendMoney":
                return new SendMoney();
            case "setAlias":
                return new SetAlias();
            case "printTransactions":
                return new PrintTransaction();
            case "checkCardStatus":
                return new CheckCardStatus();
            case "changeInterestRate":
                return new ChangeInterestRate();
            case "splitPayment":
                return new SplitPayment();


        }
        throw new IllegalArgumentException("Command not found");
    }

}
