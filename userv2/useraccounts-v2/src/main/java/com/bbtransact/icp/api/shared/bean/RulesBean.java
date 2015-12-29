package com.bbtransact.icp.api.shared.bean;

import org.springframework.stereotype.Component;

@Component
public class RulesBean {

    private String endDate;
    private String accountAccessLevel;

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAccountAccessLevel() {
        return accountAccessLevel;
    }

    public void setAccountAccessLevel(String accountAccessLevel) {
        this.accountAccessLevel = accountAccessLevel;
    }

}
