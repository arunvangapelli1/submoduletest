package com.bbtransact.icp.api.services;

import java.util.List;
import java.util.Map;

import com.bbtransact.icp.api.services.bean.v1.TransactionsVO;
import com.bbtransact.icp.api.shared.bean.TransactionBean;

public interface ITransactionService {

    public String saveTransactionInfo();

    public void updateTransactionInfo();

    public boolean deleteTransactionInfo();

    public TransactionsVO getCardHolderTransactionsInfo(String cardHolderId, String requestTimeStamp, int pageNumber,
            int pageSize, Map<String, String> allRequestParams);

    public List<TransactionBean> getAllTransactionsInfo(String cardHolderId, int pageNumber, int pageSize,
            Map<String, String> allRequestParams);

    public TransactionsVO getAccountIdTransactionsInfo(String cardHolderId, String accountId, String requestTimeStamp,
            int pageNumber, int pageSize, Map<String, String> allRequestParams);

    public List<TransactionBean> getAccountIdTransactionsInfo(String cardHolderId, String accountId, int pageNumber,
            int pageSize, Map<String, String> allRequestParams);
}