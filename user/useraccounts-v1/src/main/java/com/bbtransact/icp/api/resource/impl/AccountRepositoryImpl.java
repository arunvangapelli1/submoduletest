package com.bbtransact.icp.api.resource.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bbtransact.icp.api.exception.custom.AccountIdNotFoundException;
import com.bbtransact.icp.api.exception.custom.CardHolderNotFoundException;
import com.bbtransact.icp.api.resource.IAccountRepository;
import com.bbtransact.icp.api.resource.entity.CardHolderEntity;
import com.bbtransact.icp.api.resource.helper.AccountIdHelper;
import com.bbtransact.icp.api.resource.helper.AccountsHelper;
import com.bbtransact.icp.api.resource.repo.ICardHolderRepo;
import com.bbtransact.icp.api.shared.bean.AccountBean;
import com.bbtransact.icp.api.shared.bean.ContextBean;
import com.bbtransact.icp.api.shared.bean.OperationsBean;
import com.bbtransact.icp.api.util.DateUtil;

@Repository
public class AccountRepositoryImpl implements IAccountRepository {

    @Autowired
    private ICardHolderRepo iCardHolderRepo;

    @Autowired
    private AccountsHelper  allAccountsHelper;

    @Autowired
    private AccountIdHelper accountIdHelper;

    @Override
    public String saveAccountInfo() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateAccountInfo() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean deleteAccountInfo() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public OperationsBean getAccountOperations(String cardHolderId, String requestTimeStamp) {
        // TODO Auto-generated method stub
        CardHolderEntity accountIds = iCardHolderRepo.findOne(cardHolderId);
        if (accountIds == null) {

            throw new CardHolderNotFoundException(cardHolderId);
        }
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
    public AccountBean getAccountInfo(String cardHolderId, String accountId) {
        // TODO Auto-generated method stub

        List<String> accountIdList = new ArrayList<String>();
        accountIdList = accountIdHelper.getAccountIds(cardHolderId);
        String foundAccountId = null;
        if (accountIdList.contains(accountId)) {
            foundAccountId = accountId;
        } else {
            throw new AccountIdNotFoundException(accountId);
        }

        return allAccountsHelper.getAccountInfo(foundAccountId);
    }

    @Override
    public List<AccountBean> getAllAccountInfo(String cardHolderId) {
        // TODO Auto-generated method stub
        return allAccountsHelper.getAllAccountsInfo(cardHolderId);
    }

}
