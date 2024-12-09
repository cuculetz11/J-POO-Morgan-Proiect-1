package org.poo.services.payment;

import org.poo.entities.Bank;
import org.poo.entities.bankAccount.Account;
import org.poo.entities.transaction.Transaction;
import org.poo.entities.transaction.Transfer;
import org.poo.fileio.CommandInput;
import org.poo.services.BankingServices;

public class BankTransferStrategy implements PaymentStrategy{
    private Account sender;
    private Account receiver;
    private double amount;


    @Override
    public boolean checkForErrors(CommandInput input) {
        BankingServices bankingServices = new BankingServices();
        Account sender = Bank.getInstance().getAccounts().get(input.getAccount());
        if(sender == null) {
            return true;
        }
        Account receiver = Bank.getInstance().getAccounts().get(input.getReceiver());
        if(receiver == null) {
            return true;
        }
        if (input.getAccount().chars().allMatch(Character::isLetter) || input.getReceiver().chars().allMatch(Character::isLetter))
            return true;
        //execceptii probabil
        if (!sender.isTransferPossible(input.getAmount())) {
            Transaction transaction = new Transaction(input.getTimestamp(), "Insufficient funds");
            bankingServices.addTransactionHistory(transaction,input.getEmail());
            return true;
        }
        this.receiver = receiver;
        this.sender = sender;
        this.amount = input.getAmount();
        String money = String.valueOf(input.getAmount()) + " " + sender.getCurrency();
        Transaction transaction = new Transfer(input.getTimestamp(), input.getDescription(), sender.getIBAN(), receiver.getIBAN(), money, "sent");
        bankingServices.addTransactionHistory(transaction, sender.getUser().getEmail());
        return false;
    }

    @Override
    public void pay() {
        sender.transfer(receiver,amount);
    }
}
