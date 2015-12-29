package com.bbtransact.icp.api.resource.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "txn_entry")
public class TransactionEntryEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private String            id;
    private String            debit;
    private BigDecimal        amount;
    private char              valid;
    private CardHolderEntity  cardHolderEntity;
    private TransactionEntity transactionEntity;
    private AccountEntity     accountEntity;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "debit")
    public String getDebit() {
        return debit;
    }

    public void setDebit(String debit) {
        this.debit = debit;
    }

    @Column(name = "amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Column(name = "valid")
    public char getValid() {
        return valid;
    }

    public void setValid(char valid) {
        this.valid = valid;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARDHOLDER")
    public CardHolderEntity getCardHolderEntity() {
        return cardHolderEntity;
    }

    public void setCardHolderEntity(CardHolderEntity cardHolderEntity) {
        this.cardHolderEntity = cardHolderEntity;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "TXN")
    public TransactionEntity getTransactionEntity() {
        return transactionEntity;
    }

    public void setTransactionEntity(TransactionEntity transactionEntity) {
        this.transactionEntity = transactionEntity;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ACCOUNT")
    public AccountEntity getAccountEntity() {
        return accountEntity;
    }

    public void setAccountEntity(AccountEntity accountEntity) {
        this.accountEntity = accountEntity;
    }
}
