package com.bbtransact.icp.api.resource.entity;

import java.io.Serializable;
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
@Table(name = "issuer")
public class IssuerEntity implements Serializable {

    private static final long     serialVersionUID = 1L;

    private String                id;
    private String                name;
    private Set<CardHolderEntity> cardHolderEntity = new HashSet<CardHolderEntity>();

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "issuerEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    public Set<CardHolderEntity> getCardHolderEntity() {
        return cardHolderEntity;
    }

    public void setCardHolderEntity(Set<CardHolderEntity> cardHolderEntity) {
        this.cardHolderEntity = cardHolderEntity;
    }
}
