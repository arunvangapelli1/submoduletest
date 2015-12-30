package com.bbtransact.icp.api.resource;

import java.util.List;

import com.bbtransact.icp.api.shared.bean.AccountBean;
import com.bbtransact.icp.api.shared.bean.OperationsBean;

public interface IAccountRepository {

    public String saveAccountInfo();

    public void updateAccountInfo();

    public boolean deleteAccountInfo();

    public OperationsBean getAccountOperations(String accountHolderid, String requestTimeStamp);

    public AccountBean getAccountInfo(String accountHolderId, String accountId);

    public List<AccountBean> getAllAccountInfo(String accountHolderId);

}
