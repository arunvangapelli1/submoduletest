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
import com.bbtransact.icp.api.exception.custom.CardIdNotFoundException;
import com.bbtransact.icp.api.resource.ICardRepository;
import com.bbtransact.icp.api.shared.bean.CardBean;
import com.bbtransact.icp.api.shared.bean.OperationsBean;
import com.bbtransact.icp.api.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UserApplication.class)
@WebAppConfiguration
@Transactional
public class CardRepositoryImplUnitTestFixture {
    @Autowired
    private ICardRepository cardRepo;

    private CardBean        cardBean;
    private OperationsBean  operationsBean;
    private final String    requestTimeStamp = DateUtil.getUTCDate();

    @Test
    public void canUpdateCardInfo() {
        cardRepo.updateCardInfo(IConstants.cardId, false);

        cardBean = cardRepo.getCardInfo(IConstants.cardHolderId, IConstants.cardId);
        final boolean expected = false;
        final boolean actual = cardBean.isActive();
        assertEquals(expected, actual);

    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardIdMissingForUpdateCardInfo() {
        cardRepo.updateCardInfo(null, false);

    }

    @Test
    public void canGetAllCardDetails() {
        List<CardBean> list = cardRepo.getAllCardDetails(IConstants.cardHolderId);
        final int unexpected = 0;
        final int actual = list.size();
        assertNotEquals(unexpected, actual);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardHolderIdMissingForGetAllCardDetails() {
        cardRepo.getAllCardDetails(null);
    }

    @Test
    public void canGetCardInfo() {
        cardBean = cardRepo.getCardInfo(IConstants.cardHolderId, IConstants.cardId);
        final String expected = IConstants.cardId;
        final String actual = cardBean.getId();
        assertEquals(expected, actual);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardHolderIdMissingForGetCardInfo() {
        cardRepo.getCardInfo(null, IConstants.cardId);

    }

    @Test(expected = CardIdNotFoundException.class)
    public void cardIdMissingForGetCardInfo() {
        cardRepo.getCardInfo(IConstants.cardHolderId, null);

    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardHolderIdAndCardIdMissingForGetCardInfo() {
        cardRepo.getCardInfo(null, null);

    }

    @Test
    public void canGetCardOperations() {
        operationsBean = cardRepo.getCardOperations(IConstants.cardHolderId, requestTimeStamp);
        final int actual = operationsBean.getContexts().size();
        final int unexpected = 0;
        assertNotEquals(unexpected, actual);
    }

}
