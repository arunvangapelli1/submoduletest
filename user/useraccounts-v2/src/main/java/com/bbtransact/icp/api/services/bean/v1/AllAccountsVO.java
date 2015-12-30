package com.bbtransact.icp.api.services.bean.v1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bbtransact.icp.api.shared.bean.AccountBean;
import com.bbtransact.icp.api.shared.bean.OperationsBean;

@Component
public class AllAccountsVO {

    private List<AccountBean> accounts = new ArrayList<AccountBean>();
    private OperationsBean    operations;

    public List<AccountBean> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountBean> accounts) {
        this.accounts = accounts;
    }

    public OperationsBean getOperations() {
        return operations;
    }

    public void setOperations(OperationsBean operations) {
        this.operations = operations;
    }

}
