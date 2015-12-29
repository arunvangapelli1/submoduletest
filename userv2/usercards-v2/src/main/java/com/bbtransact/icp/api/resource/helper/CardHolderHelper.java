package com.bbtransact.icp.api.resource.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bbtransact.icp.api.exception.custom.CardHolderNotFoundException;
import com.bbtransact.icp.api.exception.custom.CardNotFoundException;
import com.bbtransact.icp.api.resource.entity.CardEntity;
import com.bbtransact.icp.api.resource.entity.CardHolderEntity;
import com.bbtransact.icp.api.resource.repo.ICardHolderRepo;
import com.bbtransact.icp.api.shared.bean.CardBean;

@Component
public class CardHolderHelper {

    @Autowired
    private ICardHolderRepo cardHolder;

    public List<CardBean> getCardHolderCards(String cardHolderId) {

        CardHolderEntity cardHolderInfo = cardHolder.findOne(cardHolderId);
        if (cardHolderInfo == null) {
            throw new CardHolderNotFoundException(cardHolderId);

        }
        CardBean tempcard = null;
        List<CardBean> cardList = new ArrayList<CardBean>();

        Set<CardEntity> cards = cardHolderInfo.getCardHoldercards();

        Iterator<CardEntity> it = cards.iterator();

        while (it.hasNext()) {
            CardEntity card = it.next();
            tempcard = new CardBean();
            tempcard.setId(card.getId());
            if ((null == card.getCardnumber()) || (card.getCardnumber().length() <= 0)) {
                tempcard.setCardNumber(card.getId());
            } else {
                tempcard.setCardNumber(card.getCardnumber());
            }
            tempcard.setCreatedDate(card.getCreateddate().toString().split(" ")[0]);
            tempcard.setStartDate(card.getStartdate().toString().split(" ")[0]);
            tempcard.setIssueDate(card.getIssueddate().toString().split(" ")[0]);
            tempcard.setExpirationDate(card.getEnddate().toString().split(" ")[0]);
            if (tempcard.getCardNumber().length() <= 3) {
                String tempCardNumber = "XXX" + tempcard.getCardNumber();
                tempcard.setCardNumberLast4(tempCardNumber.substring(tempCardNumber.length() - 4));
            } else {
                tempcard.setCardNumberLast4(tempcard.getCardNumber().substring(tempcard.getCardNumber().length() - 4));
            }
            tempcard.setActive(card.isActive());
            cardList.add(tempcard);
        }
        Collections.sort(cardList, CardBean.cardIdComparator);
        if (cardList.isEmpty()) {
            throw new CardNotFoundException(cardHolderId);

        }

        return cardList;

    }
}
