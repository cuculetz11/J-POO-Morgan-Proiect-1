package org.poo.utils;

import org.poo.command.CheckCardStatus;
import org.poo.command.Command;
import org.poo.command.SetMinBalance;
import org.poo.command.debug.PrintTransaction;
import org.poo.command.debug.PrintUsers;
import org.poo.command.report.Report;
import org.poo.command.report.SpendingReport;
import org.poo.command.transaction.*;

/*
Aceasta calsa e un fel de factory pentu comenzi
aceeasti aborfare am avut o si in tema trevute mi se pare o abordare eleganat si usoara
 */
public final class CommandManager {
    private CommandManager() {
    }

    /**
     *
     * @param commandName
     * @return retureneaza o clasa ce va executa o comanda specifica
     */
    public static Command getConcreteCommand(final String commandName) {
        return switch (commandName) {
            case "printUsers" -> new PrintUsers();
            case "addAccount" -> new AddAccount();
            case "createCard", "createOneTimeCard" -> new CreateCard();
            case "addFunds" -> new AddFounds();
            case "deleteAccount" -> new DeleteAccount();
            case "deleteCard" -> new DeleteCard();
            case "setMinimumBalance" -> new SetMinBalance();
            case "payOnline" -> new PayOnline();
            case "sendMoney" -> new SendMoney();
            case "setAlias" -> new SetAlias();
            case "printTransactions" -> new PrintTransaction();
            case "checkCardStatus" -> new CheckCardStatus();
            case "changeInterestRate" -> new ChangeInterestRate();
            case "splitPayment" -> new SplitPayment();
            case "report" -> new Report();
            case "spendingsReport" -> new SpendingReport();
            case "addInterest" -> new AddInterest();
            default -> throw new IllegalArgumentException("Command not found");
        };
    }

}
