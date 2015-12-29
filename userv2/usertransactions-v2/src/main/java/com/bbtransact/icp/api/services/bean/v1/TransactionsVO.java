package com.bbtransact.icp.api.services.bean.v1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bbtransact.icp.api.shared.bean.OperationsBean;
import com.bbtransact.icp.api.shared.bean.TransactionBean;

@Component
public class TransactionsVO {

    private List<TransactionBean> transactions = new ArrayList<TransactionBean>();
    private OperationsBean        operations;

    public List<TransactionBean> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionBean> transactions) {
        this.transactions = transactions;
    }

    public OperationsBean getOperations() {
        return operations;
    }

    public void setOperations(OperationsBean operations) {
        this.operations = operations;
    }

}
