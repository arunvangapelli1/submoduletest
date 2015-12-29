package com.bbtransact.icp.api.resource.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cardholder")
public class CardHolderEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private String            id;
    private String            firstname;
    private String            lastname;
    private String            email;
    private String            phone;
    private IssuerEntity      issuerEntity;

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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ISSUER")
    public IssuerEntity getIssuerEntity() {
        return issuerEntity;
    }
    //setter for issue entity
    public void setIssuerEntity(IssuerEntity issuerEntity) {
        this.issuerEntity = issuerEntity;

    }

}
