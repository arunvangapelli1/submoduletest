package com.bbtransact.icp.api.resource;

import java.util.List;

import com.bbtransact.icp.api.shared.bean.CardBean;
import com.bbtransact.icp.api.shared.bean.OperationsBean;

public interface ICardRepository {

    public String saveCardInfo();

    public void updateCardInfo(String cardId, boolean active);

    public boolean deleteCardInfo();

    public OperationsBean getCardOperations(String id, String requestTimeStamp);

    public CardBean getCardInfo(String cardHolderId, String cardNumber);

    public List<CardBean> getAllCardDetails(String cardHolderId);

}
