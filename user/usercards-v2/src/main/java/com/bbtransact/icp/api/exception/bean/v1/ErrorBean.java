package com.bbtransact.icp.api.exception.bean.v1;

import org.springframework.stereotype.Component;

@Component
public class ErrorBean {
    private String code;
    private String message;
    private String field;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

}
