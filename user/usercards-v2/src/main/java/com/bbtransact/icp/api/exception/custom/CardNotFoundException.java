package com.bbtransact.icp.api.exception.custom;

public class CardNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CardNotFoundException(String key) {
        super(key);
        return;
    }

    public CardNotFoundException() {
        return;

    }

}
