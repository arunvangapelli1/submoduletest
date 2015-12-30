package com.bbtransact.icp.api.resource.helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bbtransact.icp.api.exception.custom.AccountsNotFoundException;
import com.bbtransact.icp.api.resource.entity.AccountEntity;

import com.bbtransact.icp.api.resource.entity.PlanEntity;
import com.bbtransact.icp.api.resource.repo.IAllAccountsRepo;
import com.bbtransact.icp.api.shared.bean.AccountBean;
import com.bbtransact.icp.api.shared.bean.BalanceBean;
import com.bbtransact.icp.api.shared.bean.RulesBean;

@Component
public class AccountsHelper {

    @Autowired
    private IAllAccountsRepo iAllAccountsRepo;

    @Autowired
    private AccountIdHelper  accountIdHelper;

    public List<AccountBean> getAllAccountsInfo(String cardHolderId) {

        List<String> accountIdList = new ArrayList<String>();
        accountIdList = accountIdHelper.getAccountIds(cardHolderId);
        Iterator<String> accountId = accountIdList.iterator();

        List<AccountBean> allAccountsList = new ArrayList<AccountBean>();

        while (accountId.hasNext()) {

            AccountBean accounts = new AccountBean();
            RulesBean rules = new RulesBean();
            BalanceBean balance = new BalanceBean();

            AccountEntity accountEntity = iAllAccountsRepo.findOne(accountId.next());
            PlanEntity planEntity = accountEntity.getPlanEntity();
            if (accountEntity.isActive() && (planEntity.getAccountAccessLevel().equalsIgnoreCase("Full"))
                    || planEntity.getAccountAccessLevel().equalsIgnoreCase("View")) {
                accounts.setId(accountEntity.getId());
                String accountType = null;
                if (accountEntity.getAccounttype().equals("PTS")) {
                    accountType = "POINT";
                } else {
                    accountType = accountEntity.getAccounttype();
                }
                accounts.setAccountType(accountType);
                accounts.setName(planEntity.getName());
                accounts.setEndDate(accountEntity.getEndDate().toString().split(" ")[0]);
                accounts.setCurrencyCodeAlpha3("USD");
                accounts.setCurrencyCodeNumeric("840");
                rules.setEndDate(planEntity.getEndDate().toString().split(" ")[0]);
                rules.setAccountAccessLevel(planEntity.getAccountAccessLevel().toUpperCase());
                accounts.setRules(rules);
                balance.setActual(123.45);
                balance.setAvailable(123.45);
                accounts.setBalance(balance);
                allAccountsList.add(accounts);
            }
        }
        if (allAccountsList.isEmpty()) {
            throw new AccountsNotFoundException(cardHolderId);
        }

        return allAccountsList;
    }

    public AccountBean getAccountInfo(String accountId) {

        AccountBean accounts = new AccountBean();
        RulesBean rules = new RulesBean();
        BalanceBean balance = new BalanceBean();

        AccountEntity accountEntity = iAllAccountsRepo.findOne(accountId);
        PlanEntity planEntity = accountEntity.getPlanEntity();
        if (accountEntity.isActive() && (planEntity.getAccountAccessLevel().equalsIgnoreCase("Full"))
                || planEntity.getAccountAccessLevel().equalsIgnoreCase("View")) {
            accounts.setId(accountEntity.getId());
            String accountType = null;
            if (accountEntity.getAccounttype().equals("PTS")) {
                accountType = "POINT";
            } else {
                accountType = accountEntity.getAccounttype();
            }
            accounts.setAccountType(accountType);
            accounts.setName(planEntity.getName());
            accounts.setEndDate(accountEntity.getEndDate().toString().split(" ")[0]);
            accounts.setCurrencyCodeAlpha3("USD");
            accounts.setCurrencyCodeNumeric("840");
            rules.setEndDate(planEntity.getEndDate().toString().split(" ")[0]);
            rules.setAccountAccessLevel(planEntity.getAccountAccessLevel().toUpperCase());
            accounts.setRules(rules);
            balance.setActual(123.45);
            balance.setAvailable(123.45);
            accounts.setBalance(balance);
        } else {
            throw new AccountsNotFoundException(accountId);
        }

        return accounts;

    }

}
