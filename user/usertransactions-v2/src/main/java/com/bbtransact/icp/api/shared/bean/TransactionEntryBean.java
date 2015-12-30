package com.bbtransact.icp.api.shared.bean;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class TransactionEntryBean {

    private String      creditDebitType;
    private String      currencyCodeAlpha3;
    private String      currencyCodeNumeric;
    private BigDecimal  amount;
    private AccountBean account;

    public String getCreditDebitType() {
        return creditDebitType;
    }

    public void setCreditDebitType(String creditDebitType) {
        this.creditDebitType = creditDebitType;
    }

    public String getCurrencyCodeAlpha3() {
        return currencyCodeAlpha3;
    }

    public void setCurrencyCodeAlpha3(String currencyCodeAlpha3) {
        this.currencyCodeAlpha3 = currencyCodeAlpha3;
    }

    public String getCurrencyCodeNumeric() {
        return currencyCodeNumeric;
    }

    public void setCurrencyCodeNumeric(String currencyCodeNumeric) {
        this.currencyCodeNumeric = currencyCodeNumeric;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public AccountBean getAccount() {
        return account;
    }

    public void setAccount(AccountBean account) {
        this.account = account;
    }

}
