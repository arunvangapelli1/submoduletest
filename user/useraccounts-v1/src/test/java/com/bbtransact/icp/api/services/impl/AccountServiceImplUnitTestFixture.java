package com.bbtransact.icp.api.services.impl;

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
import com.bbtransact.icp.api.services.IAccountService;
import com.bbtransact.icp.api.services.bean.v1.AccountVO;
import com.bbtransact.icp.api.services.bean.v1.AllAccountsVO;
import com.bbtransact.icp.api.shared.bean.AccountBean;
import com.bbtransact.icp.api.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UserApplication.class)
@WebAppConfiguration
@Transactional
public class AccountServiceImplUnitTestFixture {
    @Autowired
    private IAccountService myAccountService;

    private AllAccountsVO   myAllAccountsVO;
    private AccountVO       myAccountVO;
    private AccountBean     myAccountBean;
    private final String    requestTimeStamp = DateUtil.getUTCDate();

    @Test
    public void canGetCardHolderAccountsInfo() {
        myAllAccountsVO = myAccountService.getCardHolderAccountsInfo(IConstants.cardHolderId, requestTimeStamp);
        final int unexpected = 0;
        final int actual = myAllAccountsVO.getAccounts().size();
        assertNotEquals(unexpected, actual);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardHolderIdMissingForGetCardHolderAccountsInfo() {
        myAccountService.getCardHolderAccountsInfo(null, requestTimeStamp);
    }

    @Test
    public void canGetCardHolderAccountInfo() {
        myAccountVO = myAccountService.getCardHolderAccountInfo(IConstants.cardHolderId, IConstants.accountId,
                requestTimeStamp);
        final String expected = IConstants.accountId;
        final String actual = myAccountVO.getId();
        assertEquals(expected, actual);
    }

    @Test(expected = AccountIdNotFoundException.class)
    public void accontIdMissingForGetCardHolderAccountInfo() {
        myAccountService.getCardHolderAccountInfo(IConstants.cardHolderId, null, requestTimeStamp);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardHolderIdMisiingForGetCardHolderAccountInfo() {
        myAccountService.getCardHolderAccountInfo(null, IConstants.accountId, requestTimeStamp);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardHolderIdAndAccountIdMissingForGetCardHolderAccountInfo() {
        myAccountService.getCardHolderAccountInfo(null, null, requestTimeStamp);
    }

    @Test
    public void canGetAccountInfo() {
        myAccountBean = myAccountService.getAccountInfo(IConstants.cardHolderId, IConstants.accountId);
        final String expected = IConstants.accountId;
        final String actual = myAccountBean.getId();
        assertEquals(expected, actual);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardHolderIdMissingForGetAccountInfo() {
        myAccountService.getAccountInfo(null, IConstants.accountId);
    }

    @Test(expected = AccountIdNotFoundException.class)
    public void accountIdMissingForGetAccountInfo() {
        myAccountService.getAccountInfo(IConstants.cardHolderId, null);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardHolderIdAndAccountIdMissingForGetAccountInfo() {
        myAccountService.getAccountInfo(null, null);
    }

    @Test
    public void canGetAllAccountsInfo() {
        List<AccountBean> list = myAccountService.getAllAccountsInfo(IConstants.cardHolderId);
        final int unexpected = 0;
        final int actual = list.size();
        assertNotEquals(unexpected, actual);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardHolderIdMissingForGetAllAccountsInfo() {
        myAccountService.getAllAccountsInfo(null);
    }

}
