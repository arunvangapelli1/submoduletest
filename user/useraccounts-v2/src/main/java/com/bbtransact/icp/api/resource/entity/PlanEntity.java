package com.bbtransact.icp.api.resource.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "plan")
public class PlanEntity {

    private String             id;
    private Date               endDate;
    private String             accountAccessLevel;
    private String             name;
    private Set<AccountEntity> accountEntity = new HashSet<AccountEntity>();

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "enddate")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "onlineaccess")
    public String getAccountAccessLevel() {
        return accountAccessLevel;
    }

    public void setAccountAccessLevel(String accountAccessLevel) {
        this.accountAccessLevel = accountAccessLevel;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "planEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    public Set<AccountEntity> getAccountEntity() {
        return accountEntity;
    }

    public void setAccountEntity(Set<AccountEntity> accountEntity) {
        this.accountEntity = accountEntity;
    }

}
