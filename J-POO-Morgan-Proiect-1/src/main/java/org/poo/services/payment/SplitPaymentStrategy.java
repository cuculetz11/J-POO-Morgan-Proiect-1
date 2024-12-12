package org.poo.services.payment;

import org.poo.entities.Bank;
import org.poo.entities.CurrencyPair;
import org.poo.entities.bankAccount.Account;
import org.poo.entities.transaction.ErrorSplitPayment;
import org.poo.entities.transaction.SplitPayment;
import org.poo.entities.transaction.Transaction;
import org.poo.fileio.CommandInput;
import org.poo.services.AccountServices;
import org.poo.services.BankingServices;

import java.util.ArrayList;

public class SplitPaymentStrategy implements PaymentStrategy {
    private ArrayList<Account> accounts = new ArrayList<>();
    private ArrayList<Double> amounts = new ArrayList<>();
    @Override
    public boolean checkForErrors(CommandInput input) {
        double amountPerUser = input.getAmount() / input.getAccounts().size();
        AccountServices accountServices = new AccountServices();
        BankingServices bankingServices = new BankingServices();
        boolean insufficientMoney = false;
        String accountWithLowMoney = null;
        for(String IBAN: input.getAccounts()) {
            if(IBAN.chars().allMatch(Character::isLetter))
                return true;
            Account account = Bank.getInstance().getAccounts().get(IBAN);
            if(account == null) {
                return true;
            }
            accounts.add(account);
            CurrencyPair currencyPair = new CurrencyPair(input.getCurrency(), account.getCurrency());
            double ammountPay = accountServices.exchangeCurrency(currencyPair, amountPerUser);
            amounts.add(ammountPay);
            if(!account.isTransferPossible(ammountPay)) {
                insufficientMoney = true;
                accountWithLowMoney = IBAN;
            }
        }
        String description = "Split payment of " + String.format("%.2f", input.getAmount()) + " " + input.getCurrency();
        String error = "Account " + accountWithLowMoney + " has insufficient funds for a split payment.";
        if(insufficientMoney) {
            Transaction transaction = new ErrorSplitPayment(input.getTimestamp(), input.getCurrency(),amountPerUser, input.getAccounts(),description,error);
            for(Account account: accounts) {
                bankingServices.addTransactionHistory(transaction,account.getUser().getEmail());
                accountServices.addTransactionToHistory(account.getIBAN(), transaction);
            }
            return true;
        }


        Transaction transaction = new SplitPayment(input.getTimestamp(), input.getCurrency(),amountPerUser,input.getAccounts(),description);
        for(Account account: accounts) {
            bankingServices.addTransactionHistory(transaction,account.getUser().getEmail());
            accountServices.addTransactionToHistory(account.getIBAN(), transaction);
        }
        return false;

    }

    @Override
    public void pay() {
        for(int i = 0 ; i < accounts.size() ; i++) {
            Account account = accounts.get(i);
            account.pay(amounts.get(i));
        }
    }
}
