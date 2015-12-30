package com.bbtransact.icp.api.resource;

import com.bbtransact.icp.api.shared.bean.CardHolderIssuerBean;
import com.bbtransact.icp.api.shared.bean.OperationsBean;

public interface ICardHolderIssuerRepository {
    public OperationsBean getCardHolderIssuerOperations(String id, String requestTimeStamp);

    public CardHolderIssuerBean getCardHolderIssuerInfo(String cardHolderId);

}
