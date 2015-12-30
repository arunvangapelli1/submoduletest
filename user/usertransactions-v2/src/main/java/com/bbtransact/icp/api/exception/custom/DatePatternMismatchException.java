package com.bbtransact.icp.api.exception.custom;

public class DatePatternMismatchException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DatePatternMismatchException(String key) {
        super(key);
        return;
    }

    public DatePatternMismatchException() {
        return;

    }
}
