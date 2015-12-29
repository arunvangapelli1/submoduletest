package com.bbtransact.icp.api.resource.helper;

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
import com.bbtransact.icp.api.shared.bean.TransactionBean;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UserApplication.class)
@WebAppConfiguration
@Transactional
public class TransactionHelperUnitTestFixture {
    @Autowired
    private TransactionHelper transactionHelper;

    @Test
    public void canGetCardHolderTransactions() {
        List<TransactionBean> list = transactionHelper.getCardHolderTransactions(IConstants.cardHolderId, 1, 10,
                Collections.emptyMap());
        final int unexpected = 0;
        final int actual = list.size();
        assertNotEquals(unexpected, actual);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardHolderIdMissingForGetCardHolderTransactions() {
        transactionHelper.getCardHolderTransactions(null, 0, 0, Collections.emptyMap());
    }

    @Test
    public void canGetAccountIdTransactions() {
        List<TransactionBean> list = transactionHelper.getAccountIdTransactions(IConstants.cardHolderId,
                IConstants.accountId, 1, 10, Collections.emptyMap());
        final int unexpected = 0;
        final int actual = list.size();
        assertNotEquals(unexpected, actual);

    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardHolderIdMissingForGetAccountIdTransactions() {

        transactionHelper.getAccountIdTransactions(null, IConstants.accountId, 1, 10, Collections.emptyMap());

    }

    @Test(expected = AccountTransactionsNotFoundException.class)
    public void accountIdMissingForGetAccountIdTransactions() {

        transactionHelper.getAccountIdTransactions(IConstants.cardHolderId, IConstants.nonExistingAccountId, 1, 0,
                Collections.emptyMap());

    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardHolderIdAndAccountIdMissingForGetAccountIdTransactions() {

        transactionHelper.getAccountIdTransactions(null, null, 1, 0, Collections.emptyMap());

    }
}
