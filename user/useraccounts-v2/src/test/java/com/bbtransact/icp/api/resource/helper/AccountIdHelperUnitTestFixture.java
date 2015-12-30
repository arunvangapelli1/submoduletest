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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UserApplication.class)
@WebAppConfiguration
@Transactional
public class AccountIdHelperUnitTestFixture {
    @Autowired
    private AccountIdHelper accountIdHelper;

    @Test
    public void canGetAccountIds() {
        List<String> list = accountIdHelper.getAccountIds(IConstants.cardHolderId);
        final int unexpected = 0;
        final int actual = list.size();
        assertNotEquals(unexpected, actual);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardHolderIdMissingForGetAccountIds() {

        accountIdHelper.getAccountIds(null);
    }

}
