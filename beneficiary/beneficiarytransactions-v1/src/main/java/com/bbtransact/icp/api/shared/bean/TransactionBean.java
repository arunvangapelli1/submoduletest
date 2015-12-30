package com.bbtransact.icp.api.shared.bean;

import org.springframework.stereotype.Component;

@Component
public class TransactionBean {

    private String               id;
    private String               transactionDateTime;
    private String               tranactionTypeCode;
    private boolean              settled;
    private String               settleDateTime;
    private MerchantBean         merchant;
    private String               cardNumberLast4;
    private TransactionEntryBean transactionEntry;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(String transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public String getTranactionTypeCode() {
        return tranactionTypeCode;
    }

    public void setTranactionTypeCode(String tranactionTypeCode) {
        this.tranactionTypeCode = tranactionTypeCode;
    }

    public boolean isSettled() {
        return settled;
    }

    public void setSettled(boolean settled) {
        this.settled = settled;
    }

    public String getSettleDateTime() {
        return settleDateTime;
    }

    public void setSettleDateTime(String settleDateTime) {
        this.settleDateTime = settleDateTime;
    }

    public MerchantBean getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantBean merchant) {
        this.merchant = merchant;
    }

    public String getCardNumberLast4() {
        return cardNumberLast4;
    }

    public void setCardNumberLast4(String cardNumberLast4) {
        this.cardNumberLast4 = cardNumberLast4;
    }

    public TransactionEntryBean getTransactionEntry() {
        return transactionEntry;
    }

    public void setTransactionEntry(TransactionEntryBean transactionEntry) {
        this.transactionEntry = transactionEntry;
    }

}
