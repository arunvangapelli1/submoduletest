package com.bbtransact.icp.api.resource.helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bbtransact.icp.api.exception.custom.CardHolderNotFoundException;
import com.bbtransact.icp.api.resource.entity.CardHolderAccountsEntity;
import com.bbtransact.icp.api.resource.entity.CardHolderEntity;
import com.bbtransact.icp.api.resource.repo.ICardHolderRepo;

@Component
public class AccountIdHelper {

    @Autowired
    private ICardHolderRepo iCardHolderRepo;

    public List<String> getAccountIds(String cardHolderId) {

        CardHolderEntity accountIds = iCardHolderRepo.findOne(cardHolderId);

        if (accountIds == null) {
            throw new CardHolderNotFoundException(cardHolderId);

        }

        String tempId = null;
        List<String> idList = new ArrayList<String>();
        Set<CardHolderAccountsEntity> ids = accountIds.getCardHolderAccounts();

        Iterator<CardHolderAccountsEntity> it = ids.iterator();

        while (it.hasNext()) {
            CardHolderAccountsEntity id = it.next();
            tempId = id.getAccountId();
            idList.add(tempId);

        }

        return idList;

    }

}
