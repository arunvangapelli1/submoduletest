package com.bbtransact.icp.api.resource;

import java.util.List;
import java.util.Map;

import com.bbtransact.icp.api.shared.bean.OperationsBean;
import com.bbtransact.icp.api.shared.bean.TransactionBean;

public interface ITransactionRepository {

    public String saveTransactionInfo();

    public void updateTransactionInfo();

    public boolean deleteTransactionInfo();

    public OperationsBean getTransactionOperations(String cardHolderid, String requestTimeStamp);

    public TransactionBean getTransactionInfo(String cardHolderId, String transactionId);

    public List<TransactionBean> getAllTransactionInfo(String cardHolderId, int pageNumber, int pageSize,
            Map<String, String> allRequestParams);

    public List<TransactionBean> getAccountIdTransactionInfo(String cardHolderId, String accountId, int pageNumber,
            int pageSize, Map<String, String> allRequestParams);
}
