package com.bbtransact.icp.api.resource.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bbtransact.icp.api.resource.ICardHolderIssuerRepository;
import com.bbtransact.icp.api.resource.helper.CardHolderIssuerHelper;
import com.bbtransact.icp.api.shared.bean.CardHolderIssuerBean;
import com.bbtransact.icp.api.shared.bean.ContextBean;
import com.bbtransact.icp.api.shared.bean.OperationsBean;
import com.bbtransact.icp.api.util.DateUtil;

@Repository
public class CardHolderIssuerRepositoryImpl implements ICardHolderIssuerRepository {
    @Autowired
    private CardHolderIssuerHelper cardHolderIssuerHelper;

    @Override
    public OperationsBean getCardHolderIssuerOperations(String id, String requestTimeStamp) {
        OperationsBean operations = new OperationsBean();
        ContextBean context = new ContextBean();

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

    @Override
    public CardHolderIssuerBean getCardHolderIssuerInfo(String cardHolderId) {

        return cardHolderIssuerHelper.getcardHolderIssuerInfo(cardHolderId);
    }

}
