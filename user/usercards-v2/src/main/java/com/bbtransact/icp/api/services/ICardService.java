package com.bbtransact.icp.api.services;

import java.util.List;

import com.bbtransact.icp.api.services.bean.v1.AllCardsVO;
import com.bbtransact.icp.api.services.bean.v1.CardVO;
import com.bbtransact.icp.api.shared.bean.CardBean;

public interface ICardService {

    public String saveCardInfo();

    public void updateCardInfo(String cardId, boolean active);

    public boolean deleteCardInfo();

    public AllCardsVO getCardHolderCardsInfo(String cardHolderId, String requestTimeStamp);

    public CardVO getCardHolderCardInfo(String cardHolderId, String cardId, String requestTimeStamp);

    public CardBean getCardInfo(String cardHolderId, String cardNumber);

    public List<CardBean> getAllCardDetails(String cardHolderId);

}