package com.bbtransact.icp.api.services.bean.v1;

import org.springframework.stereotype.Component;

import com.bbtransact.icp.api.shared.bean.IssuerBean;
import com.bbtransact.icp.api.shared.bean.OperationsBean;

@Component
public class CardHolderIssuerVO {
    private String         id;
    private IssuerBean     issuer;
    private String         givenName;
    private String         familyName;
    private String         phoneNumber;
    private String         emailAddress;
    private OperationsBean operations;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public IssuerBean getIssuer() {
        return issuer;
    }

    public void setIssuer(IssuerBean issuer) {
        this.issuer = issuer;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public OperationsBean getOperations() {
        return operations;
    }

    public void setOperations(OperationsBean operations) {
        this.operations = operations;
    }

}
