package com.bbtransact.icp.api.resource.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bbtransact.icp.api.exception.custom.CardHolderNotFoundException;
import com.bbtransact.icp.api.exception.custom.CardIdNotFoundException;
import com.bbtransact.icp.api.resource.ICardRepository;
import com.bbtransact.icp.api.resource.entity.CardHolderEntity;
import com.bbtransact.icp.api.resource.helper.CardHolderHelper;
import com.bbtransact.icp.api.resource.repo.ICardHolderRepo;
import com.bbtransact.icp.api.shared.bean.CardBean;
import com.bbtransact.icp.api.shared.bean.ContextBean;
import com.bbtransact.icp.api.shared.bean.OperationsBean;
import com.bbtransact.icp.api.resource.helper.CardHelper;
import com.bbtransact.icp.api.util.DateUtil;

/** Database layer */
@Repository
public class CardRepositoryImpl implements ICardRepository {

    @Autowired
    private OperationsBean   operations;

    @Autowired
    private ContextBean      context;

    @Autowired
    private CardHolderHelper cardHolderHelper;

    @Autowired
    private CardHelper       cardHelper;

    @Autowired
    private ICardHolderRepo  cardHolderRepo;

    @Override
    public String saveCardInfo() {
        return null;
    }

    @Override
    public void updateCardInfo(String cardId, boolean active) {
        cardHelper.updateCard(cardId, active);

    }

    @Override
    public List<CardBean> getAllCardDetails(String cardHolderId) {

        return cardHolderHelper.getCardHolderCards(cardHolderId);
    }

    @Override
    public CardBean getCardInfo(String cardHolderId, String cardId) {

        CardBean cardInfo = new CardBean();
        List<CardBean> cards = cardHolderHelper.getCardHolderCards(cardHolderId);

        if (cards != null && cards.size() > 0) {
            for (int i = 0; i < cards.size(); i++) {
                if (cards.get(i).getId().equalsIgnoreCase(cardId)) {

                    cardInfo.setActive(cards.get(i).isActive());
                    cardInfo.setId(cards.get(i).getId());
                    cardInfo.setCreatedDate(cards.get(i).getCreatedDate());
                    cardInfo.setStartDate(cards.get(i).getStartDate());
                    cardInfo.setIssueDate(cards.get(i).getIssueDate());
                    cardInfo.setExpirationDate(cards.get(i).getExpirationDate());
                    cardInfo.setCardNumber(cards.get(i).getCardNumber());
                    cardInfo.setCardNumberLast4(cards.get(i).getCardNumberLast4());

                }
            }
        }
        if (cards == null || cardInfo.getId() == null) {
            throw new CardIdNotFoundException(cardId);
        }
        return cardInfo;
    }

    @Override
    public boolean deleteCardInfo() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public OperationsBean getCardOperations(String cardHolderId, String requestTimeStamp) {
        CardHolderEntity cardHolderInfo = cardHolderRepo.findOne(cardHolderId);
        if (cardHolderInfo == null) {

            throw new CardHolderNotFoundException(cardHolderId);
        }
        operations.setResult("OK");
        operations.setErrors(new ArrayList<String>());
        List<ContextBean> contexts = new ArrayList<ContextBean>();
        context.setId("1234567890123456");
        context.setName("Taran Lent");
        context.setType("USER");
        contexts.add(context);
        operations.setContexts(contexts);
        operations.setRequestTimeStampUtc(requestTimeStamp);
        operations.setResponseTimeStampUtc(DateUtil.getUTCDate());
        return operations;
    }

}
