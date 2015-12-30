package com.bbtransact.icp.api.resource.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "card")
public class CardEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private String            id;
    private String            cardnumber;
    private Date              createddate;
    private Date              startdate;
    private Date              issueddate;
    private Date              enddate;
    private boolean           active;
    private CardHolderEntity  cardHolderEntity;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "number")
    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    @Column(name = "createddate")
    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    @Column(name = "startdate")
    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    @Column(name = "issueddate")

    public Date getIssueddate() {
        return issueddate;
    }

    public void setIssueddate(Date issueddate) {
        this.issueddate = issueddate;
    }

    @Column(name = "enddate")
    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    @Column(name = "active")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARDHOLDER")

    public CardHolderEntity getCardHolderEntity() {
        return cardHolderEntity;
    }

    public void setCardHolderEntity(CardHolderEntity cardHolderEntity) {
        this.cardHolderEntity = cardHolderEntity;
    }
}
