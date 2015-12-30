package com.bbtransact.icp.api.services.impl;

import static org.junit.Assert.assertEquals;

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
import com.bbtransact.icp.api.services.ICardHolderIssuerService;
import com.bbtransact.icp.api.services.bean.v1.CardHolderIssuerVO;
import com.bbtransact.icp.api.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UserApplication.class)
@WebAppConfiguration
@Transactional
public class CardHolderIssuerServiceImplUnitTestFixture {

    @Autowired
    private ICardHolderIssuerService myCardHolderIssuerService;

    private CardHolderIssuerVO       myCardHolderIssuerVO;
    private final String             requestTimeStamp = DateUtil.getUTCDate();

    @Test
    public void canGetCardHolderIssuer() {

        myCardHolderIssuerVO = myCardHolderIssuerService.getCardHolderIssuer(IConstants.cardHolderId, requestTimeStamp);
        final String expected = IConstants.issuerId;
        final String actual = myCardHolderIssuerVO.getIssuer().getId();
        assertEquals(expected, actual);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardHolderIdMissingForGetCardHolderIssuer() {
        myCardHolderIssuerService.getCardHolderIssuer(null, requestTimeStamp);
    }

}