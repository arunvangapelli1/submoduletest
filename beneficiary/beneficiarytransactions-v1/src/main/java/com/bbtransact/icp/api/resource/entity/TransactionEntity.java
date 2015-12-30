package com.bbtransact.icp.api.resource.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "txn")
public class TransactionEntity implements Serializable {

    private static final long           serialVersionUID = 1L;

    private String                      id;
    private Date                        date;
    private boolean                     settled;
    private Date                        settleddate;
    private String                      retcode;
    private MerchantEntity              merchantEntity;
    private CardEntity                  cardEntity;
    private Set<TransactionEntryEntity> transactionEntry = new HashSet<TransactionEntryEntity>();

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "settled")
    public boolean isSettled() {
        return settled;
    }

    public void setSettled(boolean settled) {
        this.settled = settled;
    }

    @Column(name = "settleddate")
    public Date getSettleddate() {
        return settleddate;
    }

    public void setSettleddate(Date settleddate) {
        this.settleddate = settleddate;
    }

    @Column(name = "retcode")
    public String getRetcode() {
        return retcode;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "MERCHANT")
    public MerchantEntity getMerchantEntity() {
        return merchantEntity;
    }

    public void setMerchantEntity(MerchantEntity merchantEntity) {
        this.merchantEntity = merchantEntity;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CARD")
    public CardEntity getCardEntity() {
        return cardEntity;
    }

    public void setCardEntity(CardEntity cardEntity) {
        this.cardEntity = cardEntity;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "transactionEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    public Set<TransactionEntryEntity> getTransactionEntry() {
        return transactionEntry;
    }

    public void setTransactionEntry(Set<TransactionEntryEntity> transactionEntry) {
        this.transactionEntry = transactionEntry;
    }

}
