package com.bbtransact.icp.api.resource.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cardholder_accounts")
public class CardHolderAccountsEntity {

    private String           accountId;
    private CardHolderEntity cardHolderAccountsEntity;

    @Id
    @Column(name = "account")
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARDHOLDER")
    public CardHolderEntity getCardHolderAccountsEntity() {
        return cardHolderAccountsEntity;
    }

    public void setCardHolderAccountsEntity(CardHolderEntity cardHolderAccountsEntity) {
        this.cardHolderAccountsEntity = cardHolderAccountsEntity;
    }

}
