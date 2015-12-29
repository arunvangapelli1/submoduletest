package com.bbtransact.icp.api.resource.helper;

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
import com.bbtransact.icp.api.shared.bean.AccountBean;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UserApplication.class)
@WebAppConfiguration
@Transactional
public class AccountsHelperUnitTestFixture {
    @Autowired
    private AccountsHelper accountsHelper;

    private AccountBean    accountBean;

    @Test
    public void canGetAllAccountsInfo() {

        List<AccountBean> list = accountsHelper.getAllAccountsInfo(IConstants.cardHolderId);
        final int unexpected = 0;
        final int actual = list.size();
        assertNotEquals(unexpected, actual);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void cardHolderIdMissingForGetAllAccountsInfo() {

        accountsHelper.getAllAccountsInfo(null);
    }

    @Test
    public void canGetAccountInfo() {
        accountBean = accountsHelper.getAccountInfo(IConstants.accountId);
        final String expected = IConstants.accountId;
        final String actual = accountBean.getId();
        assertEquals(expected, actual);

    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void accountIdMissingForGetAccountInfo() {
        accountsHelper.getAccountInfo(null);

    }

}
