package com.bbtransact.icp.api.exception.custom;

public class TransactionsNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TransactionsNotFoundException(String key) {
        super(key);
        return;
    }

    public TransactionsNotFoundException() {
        return;

    }

}
