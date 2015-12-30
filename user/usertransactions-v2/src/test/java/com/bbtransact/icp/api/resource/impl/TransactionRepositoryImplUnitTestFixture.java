package com.bbtransact.icp.api.resource.impl;

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
import com.bbtransact.icp.api.resource.ITransactionRepository;
import com.bbtransact.icp.api.shared.bean.OperationsBean;
import com.bbtransact.icp.api.shared.bean.TransactionBean;
import com.bbtransact.icp.api.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UserApplication.class)
@WebAppConfiguration
@Transactional
public class TransactionRepositoryImplUnitTestFixture {
    @Autowired
    private ITransactionRepository transactionRepo;

    private OperationsBean         operationsBean;
    private final String           requestTimeStamp = DateUtil.getUTCDate();

    @Test
    public void canGetTransactionOperations() {
        operationsBean = transactionRepo.getTransactionOperations(IConstants.cardHolderId, requestTimeStamp);
        final int actual = operationsBean.getContexts().size();
        final int unexpected = 0;
        assertNotEquals(unexpected, actual);
    }

    @Test
    public void canGetAllTransactionInfo() {
        List<TransactionBean> list = transactionRepo.getAllTransactionInfo(IConstants.cardHolderId, 1, 10,
                Collections.emptyMap());
        final int unexpected = 0;
        final int actual = list.size();
        assertNotEquals(unexpected, actual);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardHolderIdMissingForGetAllTransactionInfo() {

        transactionRepo.getAllTransactionInfo(null, 0, 0, Collections.emptyMap());

    }

    @Test
    public void canGetAccountIdTransactionInfo() {
        List<TransactionBean> list = transactionRepo.getAccountIdTransactionInfo(IConstants.cardHolderId,
                IConstants.accountId, 1, 10, Collections.emptyMap());
        final int unexpected = 0;
        final int actual = list.size();
        assertNotEquals(unexpected, actual);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardHolderIdMissingForGetAccountIdTransactionInfo() {

        transactionRepo.getAccountIdTransactionInfo(null, IConstants.accountId, 1, 10, Collections.emptyMap());

    }

    @Test(expected = AccountTransactionsNotFoundException.class)
    public void accountIdMissingForGetAccountIdTransactionInfo() {

        transactionRepo.getAccountIdTransactionInfo(IConstants.cardHolderId, IConstants.nonExistingAccountId, 0, 10,
                Collections.emptyMap());

    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardHolderIdAndAccountIdMissingForGetAccountIdTransactionInfo() {

        transactionRepo.getAccountIdTransactionInfo(null, null, 0, 0, Collections.emptyMap());

    }

}
