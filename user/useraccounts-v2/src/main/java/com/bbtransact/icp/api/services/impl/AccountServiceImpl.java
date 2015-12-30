package com.bbtransact.icp.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbtransact.icp.api.resource.IAccountRepository;
import com.bbtransact.icp.api.services.IAccountService;
import com.bbtransact.icp.api.services.bean.v1.AccountVO;
import com.bbtransact.icp.api.services.bean.v1.AllAccountsVO;
import com.bbtransact.icp.api.shared.bean.AccountBean;
import com.bbtransact.icp.api.shared.bean.OperationsBean;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountRepository iAccountRepository;

    @Override
    public String saveCardInfo() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateCardInfo() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean deleteCardInfo() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public AllAccountsVO getCardHolderAccountsInfo(String cardHolderId, String requestTimeStamp) {
        // TODO Auto-generated method stub
        AllAccountsVO accountsVO = new AllAccountsVO();
        accountsVO.setAccounts(getAllAccountsInfo(cardHolderId));
        accountsVO.setOperations(getAccountOperations(cardHolderId, requestTimeStamp));

        return accountsVO;

    }

    @Override
    public AccountVO getCardHolderAccountInfo(String cardHolderId, String accountId, String requestTimeStamp) {
        // TODO Auto-generated method stub
        AccountBean accountInfo = iAccountRepository.getAccountInfo(cardHolderId, accountId);

        AccountVO accountVO = new AccountVO();
        accountVO.setId(accountInfo.getId());
        accountVO.setAccountType(accountInfo.getAccountType());
        accountVO.setName(accountInfo.getName());
        accountVO.setEndDate(accountInfo.getEndDate());
        accountVO.setCurrencyCodeAlpha3(accountInfo.getCurrencyCodeAlpha3());
        accountVO.setCurrencyCodeNumeric(accountInfo.getCurrencyCodeNumeric());
        accountVO.setRules(accountInfo.getRules());
        accountVO.setBalance(accountInfo.getBalance());
        accountVO.setOperations(getAccountOperations(cardHolderId, requestTimeStamp));

        return accountVO;

    }

    @Override
    public AccountBean getAccountInfo(String cardHolderId, String accountId) {
        // TODO Auto-generated method stub
        AccountBean AccountInfo = iAccountRepository.getAccountInfo(cardHolderId, accountId);
        return AccountInfo;

    }

    @Override
    public List<AccountBean> getAllAccountsInfo(String cardHolderId) {
        // TODO Auto-generated method stub
        List<AccountBean> AccountList = iAccountRepository.getAllAccountInfo(cardHolderId);
        return AccountList;

    }

    private OperationsBean getAccountOperations(String cardHolderId, String requestTimeStamp) {
        OperationsBean operations = iAccountRepository.getAccountOperations(cardHolderId, requestTimeStamp);
        return operations;
    }

}
