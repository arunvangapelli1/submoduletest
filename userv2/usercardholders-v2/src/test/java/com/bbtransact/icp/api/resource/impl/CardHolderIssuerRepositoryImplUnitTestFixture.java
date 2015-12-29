package com.bbtransact.icp.api.resource.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
import com.bbtransact.icp.api.resource.ICardHolderIssuerRepository;
import com.bbtransact.icp.api.shared.bean.CardHolderIssuerBean;
import com.bbtransact.icp.api.shared.bean.OperationsBean;
import com.bbtransact.icp.api.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UserApplication.class)
@WebAppConfiguration
@Transactional
public class CardHolderIssuerRepositoryImplUnitTestFixture {
    @Autowired
    private ICardHolderIssuerRepository cardHolderIssuerRepository;

    private OperationsBean              operationsBean;
    private CardHolderIssuerBean        cardHolderIssuerBean;
    private final String                requestTimeStamp = DateUtil.getUTCDate();

    @Test
    public void canGetCardHolderIssuerOperations() {
        operationsBean = cardHolderIssuerRepository.getCardHolderIssuerOperations(IConstants.cardHolderId,
                requestTimeStamp);
        final int actual = operationsBean.getContexts().size();
        final int unexpected = 0;
        assertNotEquals(unexpected, actual);
    }

    @Test
    public void canGetCardHolderIssuerInfo() {
        cardHolderIssuerBean = cardHolderIssuerRepository.getCardHolderIssuerInfo(IConstants.cardHolderId);
        final String expected = IConstants.cardHolderId;
        final String actual = cardHolderIssuerBean.getId();
        assertEquals(expected, actual);

    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardHolderIdMissingForGetCardHolderIssuerInfo() {
        cardHolderIssuerRepository.getCardHolderIssuerInfo(null);
    }

}
