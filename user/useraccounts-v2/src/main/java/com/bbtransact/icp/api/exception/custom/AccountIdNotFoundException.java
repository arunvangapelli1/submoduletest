package com.bbtransact.icp.api.exception.custom;

public class AccountIdNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AccountIdNotFoundException(String key) {
        super(key);
        return;
    }

    public AccountIdNotFoundException() {
        return;

    }

}
