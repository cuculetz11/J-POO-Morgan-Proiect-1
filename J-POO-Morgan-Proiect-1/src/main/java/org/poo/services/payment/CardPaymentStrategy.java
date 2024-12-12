package org.poo.services.payment;

import org.poo.command.debug.error.NotFoundError;
import org.poo.command.debug.dto.DebugActionsDTO;
import org.poo.entities.Bank;
import org.poo.entities.CurrencyPair;
import org.poo.entities.card.Card;
import org.poo.entities.transaction.CardPayment;
import org.poo.entities.transaction.Transaction;
import org.poo.fileio.CommandInput;
import org.poo.services.AccountServices;
import org.poo.services.BankingServices;
import org.poo.utils.ErrorManager;
import org.poo.utils.JsonOutManager;

public class CardPaymentStrategy implements PaymentStrategy{
    private Card card;
    private double amountToPay;
    public boolean checkForErrors(CommandInput input) {
        BankingServices bankingServices = new BankingServices();
        Card card = Bank.getInstance().getCards().get(input.getCardNumber());
        if (card == null) {
            ErrorManager.notFound("Card not found",input.getCommand(), input.getTimestamp());
            return true;
        }
        this.card = card;
        if(card.getStatus().equals("frozen")) {
            Transaction transaction = new Transaction(input.getTimestamp(), "The card is frozen");
            bankingServices.addTransactionHistory(transaction,input.getEmail());
            return true;
        }

        AccountServices accountServices = new AccountServices();
        CurrencyPair exchangeCurrency = new CurrencyPair(input.getCurrency(),card.getAccount().getCurrency());
        double amountToPay = accountServices.exchangeCurrency(exchangeCurrency, input.getAmount());
        if(!card.getAccount().isTransferPossible(amountToPay)) {
            Transaction transaction = new Transaction(input.getTimestamp(), "Insufficient funds");
            bankingServices.addTransactionHistory(transaction,input.getEmail());
            return true;
        }
        this.amountToPay = amountToPay;
        Transaction transaction = new CardPayment(amountToPay, input.getCommerciant(), input.getTimestamp());
        bankingServices.addTransactionHistory(transaction,input.getEmail());
        accountServices.addTransactionToHistory(card.getAccount().getIBAN(),transaction);
        return false;
    }
    @Override
    public void pay() {
        card.pay(amountToPay);
    }
}
