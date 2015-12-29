package com.bbtransact.icp.api.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbtransact.icp.api.resource.ITransactionRepository;
import com.bbtransact.icp.api.services.ITransactionService;
import com.bbtransact.icp.api.services.bean.v1.TransactionsVO;
import com.bbtransact.icp.api.shared.bean.OperationsBean;
import com.bbtransact.icp.api.shared.bean.TransactionBean;

@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    ITransactionRepository iTransactionRepository;

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
    public TransactionsVO getCardHolderTransactionsInfo(String cardHolderId, String requestTimeStamp, int pageNumber,
            int pageSize, Map<String, String> allRequestParams) {
        // TODO Auto-generated method stub
        TransactionsVO transactionsVO = new TransactionsVO();
        transactionsVO.setTransactions(getAllTransactionsInfo(cardHolderId, pageNumber, pageSize, allRequestParams));
        transactionsVO.setOperations(getTransactionOperations(cardHolderId, requestTimeStamp));

        return transactionsVO;
    }

    @Override
    public List<TransactionBean> getAllTransactionsInfo(String cardHolderId, int pageNumber, int pageSize,
            Map<String, String> allRequestParams) {
        // TODO Auto-generated method stub
        List<TransactionBean> transactionList = iTransactionRepository.getAllTransactionInfo(cardHolderId, pageNumber,
                pageSize, allRequestParams);
        return transactionList;
    }

    private OperationsBean getTransactionOperations(String cardHolderId, String requestTimeStamp) {
        OperationsBean operations = iTransactionRepository.getTransactionOperations(cardHolderId, requestTimeStamp);
        return operations;
    }

    @Override
    public TransactionsVO getAccountIdTransactionsInfo(String cardHolderId, String accountId, String requestTimeStamp,
            int pageNumber, int pageSize, Map<String, String> allRequestParams) {
        // TODO Auto-generated method stub
        TransactionsVO transactionsVO = new TransactionsVO();
        transactionsVO.setTransactions(
                getAccountIdTransactionsInfo(cardHolderId, accountId, pageNumber, pageSize, allRequestParams));
        transactionsVO.setOperations(getTransactionOperations(cardHolderId, requestTimeStamp));

        return transactionsVO;

    }

    @Override
    public List<TransactionBean> getAccountIdTransactionsInfo(String cardHolderId, String accountId, int pageNumber,
            int pageSize, Map<String, String> allRequestParams) {
        // TODO Auto-generated method stub
        List<TransactionBean> transactionList = iTransactionRepository.getAccountIdTransactionInfo(cardHolderId,
                accountId, pageNumber, pageSize, allRequestParams);
        return transactionList;

    }

}
