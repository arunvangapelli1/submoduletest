package com.bbtransact.icp.api.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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
import com.bbtransact.icp.api.services.ICardService;
import com.bbtransact.icp.api.services.bean.v1.AllCardsVO;
import com.bbtransact.icp.api.services.bean.v1.CardVO;
import com.bbtransact.icp.api.shared.bean.CardBean;
import com.bbtransact.icp.api.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UserApplication.class)
@WebAppConfiguration
@Transactional
public class CardServiceImplUnitTestFixture {

    @Rule
    public ExpectedException thrown           = ExpectedException.none();

    @Autowired
    private ICardService     myCardService;

    private AllCardsVO       myAllCardsVO;
    private CardBean         myCardBean;
    private CardVO           mycardVO;
    private final String     requestTimeStamp = DateUtil.getUTCDate();

    // CardServiceImpl methods
    @Test
    public void canGetAllCardDetails() {

        List<CardBean> myCardBeanList = myCardService.getAllCardDetails(IConstants.cardHolderId);
        final int unexpected = 0;
        final int actual = myCardBeanList.size();
        assertNotEquals(unexpected, actual);
    }

    @Test
    public void canGetCardInfo() {
        myCardBean = myCardService.getCardInfo(IConstants.cardHolderId, IConstants.cardId);
        final String expected = IConstants.cardId;
        final String actual = myCardBean.getId();
        assertEquals(expected, actual);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardHolderIdAndCardIdMissingForGetCardInfo() {
        myCardService.getCardInfo(null, null);
    }

    @Test
    public void cardIdMissingForGetCardInfo() {
        thrown.expect(CardIdNotFoundException.class);
        myCardService.getCardInfo(IConstants.cardHolderId, null);
    }

    @Test
    public void cardHolderIdMissingForGetCardInfo() {
        thrown.expect(InvalidDataAccessApiUsageException.class);
        myCardService.getCardInfo(null, IConstants.cardId);
    }

    @Test
    public void canGetCardHolderCardsInfo() {
        myAllCardsVO = myCardService.getCardHolderCardsInfo(IConstants.cardHolderId, requestTimeStamp);
        final int unexpected = 0;
        final int actual = myAllCardsVO.getCards().size();
        assertNotEquals(unexpected, actual);
    }

    @Test
    public void cardHolderIdMissingForGetCardHolderCardsInfo() {
        thrown.expect(InvalidDataAccessApiUsageException.class);
        myCardService.getCardHolderCardsInfo(null, requestTimeStamp);
    }

    @Test
    public void canGetCardHolderCardInfo() {
        mycardVO = myCardService.getCardHolderCardInfo(IConstants.cardHolderId, IConstants.cardId, requestTimeStamp);
        final String expected = IConstants.cardId;
        final String actual = mycardVO.getId();
        assertEquals(expected, actual);
    }

}