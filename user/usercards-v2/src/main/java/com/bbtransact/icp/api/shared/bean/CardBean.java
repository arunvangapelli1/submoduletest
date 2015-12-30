package com.bbtransact.icp.api.shared.bean;

import java.util.Comparator;

import org.springframework.stereotype.Component;

@Component
public class CardBean {

    private String  id;
    private String  createdDate;
    private String  startDate;
    private String  issueDate;
    private String  expirationDate;
    private String  cardNumber;
    private String  cardNumberLast4;
    private boolean active;

    public CardBean(String id, String createdDate, String startDate, String issueDate, String expirationDate, String cardNumber, String cardNumberLast4, boolean active) {
        super();
        this.id = id;
        this.createdDate = createdDate;
        this.startDate = startDate;
        this.issueDate = issueDate;
        this.expirationDate = expirationDate;
        this.cardNumber = cardNumber;
        this.cardNumberLast4 = cardNumberLast4;
        this.active = active;
    }

    public CardBean() {

    }

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

    public static Comparator<CardBean> cardIdComparator = new Comparator<CardBean>() {
        public int compare(CardBean c1, CardBean c2) {

            String cardId1 = c1.getId();
            String cardId2 = c2.getId();

            return cardId1.compareTo(cardId2);

        }
    };

    @Override
    public String toString() {
        return "[ id=" + id + "]";
    }

}
