package com.bbtransact.icp.api.exception.custom;

public class AccountTransactionsNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AccountTransactionsNotFoundException(String key) {
        super(key);
        return;
    }

    public AccountTransactionsNotFoundException() {
        return;

    }

}
