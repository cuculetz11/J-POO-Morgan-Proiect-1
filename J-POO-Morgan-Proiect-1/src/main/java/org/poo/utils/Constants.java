package org.poo.utils;

public final  class Constants {
    private Constants() { }
    public static final String ACCOUNT_NOT_FOUND = "Account not found";
    public static final String SAVINGS = "savings";
    public static final String NOT_CLASSIC_ACCOUNT =
            "This kind of report is not supported for a saving account";
    public static final String NOT_SAVINGS_ACCOUNT = "This is not a savings account";
    public static final String CARD_PAYMENT = "Card payment";
    public static final String NEW_ACCOUNT_CREATED = "New account created";
    public static final String INSUFFICIENT_FUNDS = "Insufficient funds";
    public static final String SENT = "sent";
    public static final String RECEIVED = "received";
    public static final String THE_CARD_HAS_BEEN_DESTROYED = "The card has been destroyed";
    public static final String CARD_NOT_FOUND = "Card not found";
    public static final String ACCOUNT_DELETED = "Account deleted";
    public static final String ACCOUNT_CANT_BE_DELETED =
            "Account couldn't be deleted - see org.poo.transactions for details";
    public static final String ACCOUNT_CANT_BE_DELETED_FUNDS =
            "Account couldn't be deleted - there are funds remaining";
}
