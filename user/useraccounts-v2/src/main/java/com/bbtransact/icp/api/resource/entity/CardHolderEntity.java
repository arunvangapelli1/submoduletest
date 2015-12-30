package com.bbtransact.icp.api.resource.entity;

import java.io.Serializable;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "cardholder")
public class CardHolderEntity implements Serializable {

    private static final long             serialVersionUID   = 1L;
    private String                        id;
    private String                        firstname;
    private String                        lastname;
    private String                        email;
    private String                        phone;
    private Set<CardHolderAccountsEntity> cardHolderAccounts = new HashSet<CardHolderAccountsEntity>();

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cardHolderAccountsEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy
    public Set<CardHolderAccountsEntity> getCardHolderAccounts() {
        return cardHolderAccounts;
    }

    public void setCardHolderAccounts(Set<CardHolderAccountsEntity> cardHolderAccounts) {
        this.cardHolderAccounts = cardHolderAccounts;
    }

}
