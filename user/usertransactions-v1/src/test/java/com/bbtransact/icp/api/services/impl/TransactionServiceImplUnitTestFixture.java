package com.bbtransact.icp.api.services.impl;

import static org.junit.Assert.assertNotEquals;

import java.util.Collections;
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
import com.bbtransact.icp.api.exception.custom.AccountTransactionsNotFoundException;
import com.bbtransact.icp.api.services.ITransactionService;
import com.bbtransact.icp.api.services.bean.v1.TransactionsVO;
import com.bbtransact.icp.api.shared.bean.TransactionBean;
import com.bbtransact.icp.api.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UserApplication.class)
@WebAppConfiguration
@Transactional
public class TransactionServiceImplUnitTestFixture {

    @Autowired
    private ITransactionService myTransactionService;

    private TransactionsVO      myTransactionsVO;
    private final String        requestTimeStamp = DateUtil.getUTCDate();

    @Test
    public void canGetCardHolderTransactionsInfo() {
        myTransactionsVO = myTransactionService.getCardHolderTransactionsInfo(IConstants.cardHolderId, requestTimeStamp,
                1, 10, Collections.emptyMap());
        final int unexpected = 0;
        final int actual = myTransactionsVO.getTransactions().size();
        assertNotEquals(unexpected, actual);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardHolderIdMissingForGetCardHolderTransactionsInfo() {
        myTransactionService.getCardHolderTransactionsInfo(null, requestTimeStamp, 1, 10, Collections.emptyMap());

    }

    @Test
    public void canGetAllTransactionsInfo() {
        final List<TransactionBean> list = myTransactionService.getAllTransactionsInfo(IConstants.cardHolderId, 1, 10,
                Collections.emptyMap());
        final int unexpected = 0;
        final int actual = list.size();
        assertNotEquals(unexpected, actual);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardHolderIdMissingForGetAllTransactionsInfo() {
        myTransactionService.getAllTransactionsInfo(null, 0, 0, Collections.emptyMap());
    }

    @Test
    public void canGetAccountIdTransactionsInfo() {
        myTransactionsVO = myTransactionService.getAccountIdTransactionsInfo(IConstants.cardHolderId,
                IConstants.accountId, requestTimeStamp, 1, 10, Collections.emptyMap());
        final int unexpected = 0;
        final int actual = myTransactionsVO.getTransactions().size();
        assertNotEquals(unexpected, actual);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void CardHolderIdMissingForGetAccountIdTransactionsInfo() {
        myTransactionService.getAccountIdTransactionsInfo(null, IConstants.accountId, requestTimeStamp, 0, 0,
                Collections.emptyMap());
    }

    @Test(expected = AccountTransactionsNotFoundException.class)
    public void accountIdMissingForGetAccountIdTransactionsInfo() {
        myTransactionService.getAccountIdTransactionsInfo(IConstants.cardHolderId, IConstants.nonExistingAccountId,
                requestTimeStamp, 0, 0, Collections.emptyMap());
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardHolderIdAndAccountIdMissingForGetAccountIdTransactionsInfo() {
        myTransactionService.getAccountIdTransactionsInfo(null, null, requestTimeStamp, 0, 0, Collections.emptyMap());
    }

    @Test
    public void canGetAccountIdTransactionsListInfo() {
        final List<TransactionBean> list = myTransactionService.getAccountIdTransactionsInfo(IConstants.cardHolderId,
                IConstants.accountId, 1, 10, Collections.emptyMap());
        final int unexpected = 0;
        final int actual = list.size();
        assertNotEquals(unexpected, actual);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardHolderIdMissingForGetAccountIdTransactionsListInfo() {
        myTransactionService.getAccountIdTransactionsInfo(null, IConstants.accountId, 0, 0, Collections.emptyMap());
    }

    @Test(expected = AccountTransactionsNotFoundException.class)
    public void accountIdMissingForGetAccountIdTransactionsListInfo() {
        myTransactionService.getAccountIdTransactionsInfo(IConstants.cardHolderId, IConstants.nonExistingAccountId, 0,
                0, Collections.emptyMap());
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardHolderIdAndAccountIdMissingForGetAccountIdTransactionsListInfo() {
        myTransactionService.getAccountIdTransactionsInfo(null, null, 0, 0, Collections.emptyMap());
    }
}
