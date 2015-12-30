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
@Table(name = "account")
public class AccountEntity implements Serializable {

    private static final long           serialVersionUID = 1L;
    private String                      id;
    private String                      accounttype;
    private Date                        endDate;
    private boolean                     active;
    private PlanEntity                  planEntity;
    private Set<TransactionEntryEntity> transactionEntry = new HashSet<TransactionEntryEntity>();

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "accounttype")
    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    @Column(name = "enddate")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "active")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "PLAN")
    public PlanEntity getPlanEntity() {
        return planEntity;
    }

    public void setPlanEntity(PlanEntity planEntity) {
        this.planEntity = planEntity;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accountEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    public Set<TransactionEntryEntity> getTransactionEntry() {
        return transactionEntry;
    }

    public void setTransactionEntry(Set<TransactionEntryEntity> transactionEntry) {
        this.transactionEntry = transactionEntry;
    }
}
