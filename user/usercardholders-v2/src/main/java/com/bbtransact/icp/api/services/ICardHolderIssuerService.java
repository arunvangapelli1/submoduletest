package com.bbtransact.icp.api.services;

import com.bbtransact.icp.api.services.bean.v1.CardHolderIssuerVO;

public interface ICardHolderIssuerService {
    public CardHolderIssuerVO getCardHolderIssuer(String cardHolderId, String requestTimeStamp);
}
