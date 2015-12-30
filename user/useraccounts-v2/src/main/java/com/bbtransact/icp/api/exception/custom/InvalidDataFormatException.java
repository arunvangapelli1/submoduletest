package com.bbtransact.icp.api.exception.custom;

public class InvalidDataFormatException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidDataFormatException(String key) {
        super(key);
        return;
    }

    public InvalidDataFormatException() {
        return;

    }
}
