package com.bbtransact.icp.api.services;

import java.util.List;

import com.bbtransact.icp.api.services.bean.v1.AccountVO;
import com.bbtransact.icp.api.services.bean.v1.AllAccountsVO;
import com.bbtransact.icp.api.shared.bean.AccountBean;

public interface IAccountService {

    public String saveCardInfo();

    public void updateCardInfo();

    public boolean deleteCardInfo();

    public AllAccountsVO getCardHolderAccountsInfo(String cardHolderId, String requestTimeStamp);

    public AccountVO getCardHolderAccountInfo(String cardHolderId, String accountId, String requestTimeStamp);

    public AccountBean getAccountInfo(String cardHolderId, String accountId);

    public List<AccountBean> getAllAccountsInfo(String cardHolderId);

}
