package com.bbtransact.icp.api.services.bean.v1;

import org.springframework.stereotype.Component;

import com.bbtransact.icp.api.shared.bean.OperationsBean;

@Component
public class CardVO {

    private String         id;
    private String         createdDate;
    private String         startDate;
    private String         issueDate;
    private String         expirationDate;
    private String         cardNumber;
    private String         cardNumberLast4;
    private boolean        active;
    private OperationsBean operations;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumberLast4() {
        return cardNumberLast4;
    }

    public void setCardNumberLast4(String cardNumberLast4) {
        this.cardNumberLast4 = cardNumberLast4;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public OperationsBean getOperations() {
        return operations;
    }

    public void setOperations(OperationsBean operations) {
        this.operations = operations;
    }

}