package com.bbtransact.icp.api.resource.helper;

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
import com.bbtransact.icp.api.shared.bean.CardBean;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UserApplication.class)
@WebAppConfiguration
@Transactional
public class CardHolderHelperUnitTestFixture {
    @Autowired
    private CardHolderHelper cardHolderHelper;

    @Test
    public void canGetCardHolderCards() {
        List<CardBean> list = cardHolderHelper.getCardHolderCards(IConstants.cardHolderId);
        final int unexpected = 0;
        final int actual = list.size();
        assertNotEquals(unexpected, actual);

    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardHolderIdMissingForGetCardHolderCards() {
        cardHolderHelper.getCardHolderCards(null);
    }
}
