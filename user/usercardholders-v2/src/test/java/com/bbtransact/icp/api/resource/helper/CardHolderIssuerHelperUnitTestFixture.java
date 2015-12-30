package com.bbtransact.icp.api.resource.helper;

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
import com.bbtransact.icp.api.shared.bean.CardHolderIssuerBean;
import com.bbtransact.icp.api.shared.bean.IssuerBean;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UserApplication.class)
@WebAppConfiguration
@Transactional
public class CardHolderIssuerHelperUnitTestFixture {
    @Autowired
    private CardHolderIssuerHelper cardHolderIssuerHelper;

    private CardHolderIssuerBean   cardHolderIssuerBean;
    private IssuerBean             issuerBean;

    @Test
    public void canGetcardHolderIssuerInfo() {
        cardHolderIssuerBean = cardHolderIssuerHelper.getcardHolderIssuerInfo(IConstants.cardHolderId);
        issuerBean = cardHolderIssuerBean.getIssuer();
        final String expected = IConstants.issuerId;
        final String actual = issuerBean.getId();
        assertEquals(expected, actual);

    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardHolderIdMissingForGetcardHolderIssuerInfo() {
        cardHolderIssuerHelper.getcardHolderIssuerInfo(null);

    }

}
