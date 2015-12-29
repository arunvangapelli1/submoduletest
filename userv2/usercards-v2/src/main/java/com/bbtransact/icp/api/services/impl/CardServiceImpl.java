package com.bbtransact.icp.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbtransact.icp.api.resource.ICardRepository;
import com.bbtransact.icp.api.services.ICardService;
import com.bbtransact.icp.api.services.bean.v1.AllCardsVO;
import com.bbtransact.icp.api.services.bean.v1.CardVO;
import com.bbtransact.icp.api.shared.bean.CardBean;
import com.bbtransact.icp.api.shared.bean.OperationsBean;

@Service
public class CardServiceImpl implements ICardService {

    @Autowired
    private ICardRepository iCardRepository;

    @Override
    public String saveCardInfo() {
        return null;
    }

    @Override
    public List<CardBean> getAllCardDetails(String cardHolderId) {

        List<CardBean> CardList = iCardRepository.getAllCardDetails(cardHolderId);
        return CardList;
    }

    @Override
    public CardBean getCardInfo(String cardHolderId, String cardNumber) {
        CardBean CardInfo = iCardRepository.getCardInfo(cardHolderId, cardNumber);
        return CardInfo;
    }

    @Override
    public void updateCardInfo(String cardId, boolean active) {
        iCardRepository.updateCardInfo(cardId, active);

    }

    @Override
    public boolean deleteCardInfo() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public AllCardsVO getCardHolderCardsInfo(String cardHolderId, String requestTimeStamp) {
        AllCardsVO allCardsVO = new AllCardsVO();
        allCardsVO.setOperations(getCardOperations(cardHolderId, requestTimeStamp));
        allCardsVO.setCards(getAllCardDetails(cardHolderId));

        return allCardsVO;
    }

    @Override
    public CardVO getCardHolderCardInfo(String cardHolderId, String cardId, String requestTimeStamp) {

        CardBean cardInfo = iCardRepository.getCardInfo(cardHolderId, cardId);
        CardVO cardVO = new CardVO();
        cardVO.setId(cardInfo.getId());
        cardVO.setCreatedDate(cardInfo.getCreatedDate());
        cardVO.setStartDate(cardInfo.getStartDate());
        cardVO.setIssueDate(cardInfo.getIssueDate());
        cardVO.setExpirationDate(cardInfo.getExpirationDate());
        cardVO.setCardNumber(cardInfo.getCardNumber());
        cardVO.setCardNumberLast4(cardInfo.getCardNumberLast4());
        cardVO.setActive(cardInfo.isActive());
        cardVO.setOperations(getCardOperations(cardHolderId, requestTimeStamp));

        return cardVO;
    }

    private OperationsBean getCardOperations(String id, String requestTimeStamp) {
        OperationsBean operations = iCardRepository.getCardOperations(id, requestTimeStamp);
        return operations;
    }

}