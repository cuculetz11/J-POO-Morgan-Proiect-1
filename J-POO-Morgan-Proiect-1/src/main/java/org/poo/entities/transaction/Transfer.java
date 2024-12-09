package org.poo.entities.transaction;

public class Transfer extends Transaction {
    String senderIBAN;
    String receiverIBAN;
    String amount;
    String transferType;

    public Transfer(int timestamp,String description, String senderIBAN, String receiverIBAN, String amount, String transferType) {
        super(timestamp,description);
        this.senderIBAN = senderIBAN;
        this.receiverIBAN = receiverIBAN;
        this.amount = amount;
        this.transferType = transferType;

    }

    public String getSenderIBAN() {
        return senderIBAN;
    }

    public String getTransferType() {
        return transferType;
    }

    public String getAmount() {
        return amount;
    }

    public String getReceiverIBAN() {
        return receiverIBAN;
    }
}
