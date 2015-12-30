package com.bbtransact.icp.api.resource.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bbtransact.icp.api.exception.custom.CardIdNotFoundException;
import com.bbtransact.icp.api.resource.entity.CardEntity;
import com.bbtransact.icp.api.resource.repo.ICardRepo;

@Component
public class CardHelper {

    @Autowired
    private ICardRepo iCardRepo;

    public void updateCard(String cardId, boolean active) {
        CardEntity cardDetail = iCardRepo.findOne(cardId);
        if (cardDetail == null) {
            throw new CardIdNotFoundException(cardId);
        }
        cardDetail.setActive(active);
        iCardRepo.save(cardDetail);

    }

}
