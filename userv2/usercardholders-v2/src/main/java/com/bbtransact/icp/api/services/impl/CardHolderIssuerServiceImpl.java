package com.bbtransact.icp.api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbtransact.icp.api.resource.ICardHolderIssuerRepository;
import com.bbtransact.icp.api.services.ICardHolderIssuerService;
import com.bbtransact.icp.api.services.bean.v1.CardHolderIssuerVO;
import com.bbtransact.icp.api.shared.bean.CardHolderIssuerBean;
import com.bbtransact.icp.api.shared.bean.OperationsBean;

@Service
public class CardHolderIssuerServiceImpl implements ICardHolderIssuerService {
    @Autowired
    private ICardHolderIssuerRepository iCardHolderIssuerRepository;

    @Override
    public CardHolderIssuerVO getCardHolderIssuer(String cardHolderId, String requestTimeStamp) {
        CardHolderIssuerBean cardHolderIssuerBean = iCardHolderIssuerRepository.getCardHolderIssuerInfo(cardHolderId);
        CardHolderIssuerVO cardHolderIssuerVO = new CardHolderIssuerVO();
        cardHolderIssuerVO.setId(cardHolderIssuerBean.getId());
        cardHolderIssuerVO.setGivenName(cardHolderIssuerBean.getGivenName());
        cardHolderIssuerVO.setFamilyName(cardHolderIssuerBean.getFamilyName());
        cardHolderIssuerVO.setEmailAddress(cardHolderIssuerBean.getEmailAddress());
        cardHolderIssuerVO.setPhoneNumber(cardHolderIssuerBean.getPhoneNumber());
        cardHolderIssuerVO.setIssuer(cardHolderIssuerBean.getIssuer());
        cardHolderIssuerVO.setOperations(getCardHolderIssuerOperations(cardHolderId, requestTimeStamp));
        return cardHolderIssuerVO;
    }

    private OperationsBean getCardHolderIssuerOperations(String cardHolderId, String requestTimeStamp) {
        OperationsBean operations = iCardHolderIssuerRepository.getCardHolderIssuerOperations(cardHolderId,
                requestTimeStamp);
        return operations;
    }
}
