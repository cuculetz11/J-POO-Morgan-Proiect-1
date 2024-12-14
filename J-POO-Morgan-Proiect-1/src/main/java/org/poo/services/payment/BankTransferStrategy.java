package org.poo.services.payment;

import org.poo.entities.Bank;
import org.poo.entities.CurrencyPair;
import org.poo.entities.bankAccount.Account;
import org.poo.entities.transaction.Transaction;
import org.poo.entities.transaction.Transfer;
import org.poo.fileio.CommandInput;
import org.poo.utils.Constants;

public class BankTransferStrategy implements PaymentStrategy {
    private Account sender;
    private Account receiver;
    private double amount;


    @Override
    public boolean checkForErrors(final CommandInput input) {

        Account sender = Bank.getInstance().getAccounts().get(input.getAccount());
        if (sender == null) {
            return true;
        }
        Account receiver = Bank.getInstance().getAccounts().get(input.getReceiver());
        if (receiver == null) {
            return true;
        }
        if (input.getAccount().chars().allMatch(Character::isLetter))
            return true;
        //execceptii probabil
        if (sender.isTransferPossible(input.getAmount())) {
            Transaction transaction = new Transaction(input.getTimestamp(), Constants.INSUFFICIENT_FUNDS);
            bankingServices.addTransactionHistory(transaction, input.getEmail());
            accountServices.addTransactionToHistory(sender.getIban(), transaction);
            return true;
        }
        this.receiver = receiver;
        this.sender = sender;
        this.amount = input.getAmount();

        String money = input.getAmount() + " " + sender.getCurrency();
        Transaction sentTransaction = new Transfer(input.getTimestamp(), input.getDescription(), sender.getIban(), receiver.getIban(), money, Constants.SENT);
        bankingServices.addTransactionHistory(sentTransaction, sender.getUser().getEmail());
        accountServices.addTransactionToHistory(input.getAccount(), sentTransaction);

        double exchangedAmount = accountServices.exchangeCurrency(new CurrencyPair(sender.getCurrency(), receiver.getCurrency()), amount);
        String exchangedMoney = exchangedAmount + " " + receiver.getCurrency();

        Transaction receivedTransaction = new Transfer(input.getTimestamp(), input.getDescription(), sender.getIban(), receiver.getIban(), exchangedMoney, Constants.RECEIVED);
        bankingServices.addTransactionHistory(receivedTransaction, receiver.getUser().getEmail());
        accountServices.addTransactionToHistory(receiver.getIban(), receivedTransaction);
        return false;
    }

    @Override
    public void pay() {
        sender.transfer(receiver, amount);
    }
}
