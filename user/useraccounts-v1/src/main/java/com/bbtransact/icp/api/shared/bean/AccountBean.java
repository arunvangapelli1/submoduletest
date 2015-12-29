package com.bbtransact.icp.api.shared.bean;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.stereotype.Component;

@Component
@JsonSerialize(include = Inclusion.NON_NULL)
public class AccountBean {

    private String      id;
    private String      accountType;
    private String      name;
    private String      endDate;
    private String      currencyCodeAlpha3;
    private String      currencyCodeNumeric;

    private RulesBean   rules;
    private BalanceBean balance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public RulesBean getRules() {
        return rules;
    }

    public void setRules(RulesBean rules) {
        this.rules = rules;
    }

    public BalanceBean getBalance() {
        return balance;
    }

    public void setBalance(BalanceBean balance) {
        this.balance = balance;
    }

}
