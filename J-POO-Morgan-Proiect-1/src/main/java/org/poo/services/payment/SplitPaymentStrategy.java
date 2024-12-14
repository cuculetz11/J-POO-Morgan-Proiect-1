package org.poo.services.payment;

import org.poo.entities.Bank;
import org.poo.entities.CurrencyPair;
import org.poo.entities.bankAccount.Account;
import org.poo.entities.transaction.ErrorSplitPayment;
import org.poo.entities.transaction.SplitPayment;
import org.poo.entities.transaction.Transaction;
import org.poo.fileio.CommandInput;

import java.util.ArrayList;

public class SplitPaymentStrategy implements PaymentStrategy {
    private final ArrayList<Account> accounts = new ArrayList<>();
    private final ArrayList<Double> amounts = new ArrayList<>();

    @Override
    public boolean checkForErrors(final CommandInput input) {

        double amountPerUser = input.getAmount() / input.getAccounts().size();
        boolean insufficientMoney = false;
        String accountWithLowMoney = null;
        for (String iban : input.getAccounts()) {
            if (iban.chars().allMatch(Character::isLetter)) {
                return true;
            }
            Account account = Bank.getInstance().getAccounts().get(iban);
            if (account == null) {
                return true;
            }
            accounts.add(account);
            CurrencyPair currencyPair = new CurrencyPair(input.getCurrency(), account.getCurrency());
            double ammountPay = accountServices.exchangeCurrency(currencyPair, amountPerUser);
            amounts.add(ammountPay);
            if (account.isTransferPossible(ammountPay)) {
                insufficientMoney = true;
                accountWithLowMoney = iban;
            }
        }
        String description = "Split payment of " + String.format("%.2f", input.getAmount()) + " " + input.getCurrency();
        String error = "Account " + accountWithLowMoney + " has insufficient funds for a split payment.";
        if (insufficientMoney) {
            Transaction transaction = new ErrorSplitPayment(input.getTimestamp(), input.getCurrency(), amountPerUser, input.getAccounts(), description, error);
            for (Account account : accounts) {
                bankingServices.addTransactionHistory(transaction, account.getUser().getEmail());
                accountServices.addTransactionToHistory(account.getIban(), transaction);
            }
            return true;
        }


        Transaction transaction = new SplitPayment(input.getTimestamp(), input.getCurrency(), amountPerUser, input.getAccounts(), description);
        for (Account account : accounts) {
            bankingServices.addTransactionHistory(transaction, account.getUser().getEmail());
            accountServices.addTransactionToHistory(account.getIban(), transaction);
        }
        return false;

    }

    @Override
    public void pay() {
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            account.pay(amounts.get(i));
        }
    }
}
