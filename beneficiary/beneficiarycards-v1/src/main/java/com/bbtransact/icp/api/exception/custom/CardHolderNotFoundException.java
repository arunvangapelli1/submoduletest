package com.bbtransact.icp.api.exception.custom;

public class CardHolderNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CardHolderNotFoundException(String key) {
        super(key);
        return;
    }

    public CardHolderNotFoundException() {
        return;

    }
}
