package com.bbtransact.icp.api.exception.custom;

public class AccountsNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AccountsNotFoundException(String key) {
        super(key);
        return;
    }

    public AccountsNotFoundException() {
        return;

    }

}
