package com.bbtransact.icp.api.exception.custom;

public class CardIdNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CardIdNotFoundException(String key) {
        super(key);
        return;
    }

    public CardIdNotFoundException() {
        return;

    }
}
