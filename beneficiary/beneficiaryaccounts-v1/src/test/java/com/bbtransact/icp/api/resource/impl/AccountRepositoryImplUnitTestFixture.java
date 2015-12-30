package com.bbtransact.icp.api.resource.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bbtransact.icp.api.boot.IConstants;
import com.bbtransact.icp.api.boot.UserApplication;
import com.bbtransact.icp.api.exception.custom.AccountIdNotFoundException;
import com.bbtransact.icp.api.resource.IAccountRepository;
import com.bbtransact.icp.api.shared.bean.AccountBean;
import com.bbtransact.icp.api.shared.bean.OperationsBean;
import com.bbtransact.icp.api.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UserApplication.class)
@WebAppConfiguration
@Transactional
public class AccountRepositoryImplUnitTestFixture {

    @Autowired
    private IAccountRepository accountRepo;

    private AccountBean        accountBean;
    private OperationsBean     operationsBean;
    private final String       requestTimeStamp = DateUtil.getUTCDate();

    @Test
    public void canGetAccountOperations() {
        operationsBean = accountRepo.getAccountOperations(IConstants.cardHolderId, requestTimeStamp);
        final int actual = operationsBean.getContexts().size();
        final int unexpected = 0;
        assertNotEquals(unexpected, actual);

    }

    @Test
    public void canGetAccountInfo() {
        accountBean = accountRepo.getAccountInfo(IConstants.cardHolderId, IConstants.accountId);
        final String expected = IConstants.accountId;
        final String actual = accountBean.getId();
        assertEquals(expected, actual);

    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardHolderIdMissingForGetAccountInfo() {
        accountRepo.getAccountInfo(null, IConstants.accountId);
    }

    @Test(expected = AccountIdNotFoundException.class)
    public void accountIdMissingForGetAccountInfo() {
        accountRepo.getAccountInfo(IConstants.cardHolderId, null);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardHolderIdAndAccountIdMissingForGetAccountInfo() {
        accountRepo.getAccountInfo(null, null);
    }

    @Test
    public void canGetAllAccountInfo() {
        List<AccountBean> list = accountRepo.getAllAccountInfo(IConstants.cardHolderId);
        final int unexpected = 0;
        final int actual = list.size();
        assertNotEquals(unexpected, actual);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardHolderIdMissingForGetAllAccountInfo() {
        accountRepo.getAllAccountInfo(null);
    }

}
