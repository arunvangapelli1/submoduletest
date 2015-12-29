package com.bbtransact.icp.api.resource.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bbtransact.icp.api.exception.custom.CardHolderNotFoundException;
import com.bbtransact.icp.api.resource.entity.CardHolderEntity;
import com.bbtransact.icp.api.resource.entity.IssuerEntity;
import com.bbtransact.icp.api.resource.repo.ICardHolderRepo;
import com.bbtransact.icp.api.shared.bean.CardHolderIssuerBean;
import com.bbtransact.icp.api.shared.bean.IssuerBean;

@Component
public class CardHolderIssuerHelper {
    @Autowired
    private ICardHolderRepo cardHolder;

    public CardHolderIssuerBean getcardHolderIssuerInfo(String cardHolderId) {
        CardHolderEntity cardHolderInfo = cardHolder.findOne(cardHolderId);
        if (cardHolderInfo == null) {
            throw new CardHolderNotFoundException(cardHolderId);

        }
        IssuerEntity issuerEntity = cardHolderInfo.getIssuerEntity();

        CardHolderIssuerBean cardHolderIssuerBean = new CardHolderIssuerBean();
        IssuerBean issuerBean = new IssuerBean();

        cardHolderIssuerBean.setId(cardHolderInfo.getId());
        cardHolderIssuerBean.setGivenName(cardHolderInfo.getFirstname());
        cardHolderIssuerBean.setFamilyName(cardHolderInfo.getLastname());
        cardHolderIssuerBean.setEmailAddress(cardHolderInfo.getEmail());
        cardHolderIssuerBean.setPhoneNumber(cardHolderInfo.getPhone());
        issuerBean.setId(issuerEntity.getId());
        issuerBean.setName(issuerEntity.getName());
        issuerBean.setLocaleId("en-US");
        cardHolderIssuerBean.setIssuer(issuerBean);

        return cardHolderIssuerBean;
    }
}
