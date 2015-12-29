package com.bbtransact.icp.api.resource.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bbtransact.icp.api.resource.ITransactionRepository;
import com.bbtransact.icp.api.resource.helper.TransactionHelper;
import com.bbtransact.icp.api.shared.bean.ContextBean;
import com.bbtransact.icp.api.shared.bean.OperationsBean;
import com.bbtransact.icp.api.shared.bean.TransactionBean;
import com.bbtransact.icp.api.util.DateUtil;

@Repository
public class TransactionRepositoryImpl implements ITransactionRepository {

    @Autowired
    TransactionHelper transactionHelper;

    @Override
    public String saveTransactionInfo() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateTransactionInfo() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean deleteTransactionInfo() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public OperationsBean getTransactionOperations(String cardHolderid, String requestTimeStamp) {
        // TODO Auto-generated method stub
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
    public List<TransactionBean> getAllTransactionInfo(String cardHolderId, int pageNumber, int pageSize,
            Map<String, String> allRequestParams) {

        return transactionHelper.getCardHolderTransactions(cardHolderId, pageNumber, pageSize, allRequestParams);
    }

    @Override
    public TransactionBean getTransactionInfo(String cardHolderId, String transactionId) {

        return null;
    }

    @Override
    public List<TransactionBean> getAccountIdTransactionInfo(String cardHolderId, String accountId, int pageNumber,
            int pageSize, Map<String, String> allRequestParams) {

        return transactionHelper.getAccountIdTransactions(cardHolderId, accountId, pageNumber, pageSize,
                allRequestParams);
    }

}