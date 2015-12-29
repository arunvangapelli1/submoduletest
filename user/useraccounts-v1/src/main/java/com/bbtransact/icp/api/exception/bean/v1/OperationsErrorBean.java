package com.bbtransact.icp.api.exception.bean.v1;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bbtransact.icp.api.util.DateUtil;

@Component
public class OperationsErrorBean {

    private String                 result;
    private List<ErrorBean>        errors;
    private List<ContextErrorBean> errorContexts;

    public List<ContextErrorBean> getErrorContexts() {
        return errorContexts;
    }

    public void setErrorContexts(List<ContextErrorBean> errorContexts) {
        this.errorContexts = errorContexts;
    }

    private String requestTimeStampUtc;
    private String responseTimeStampUtc;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<ErrorBean> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorBean> errors) {
        this.errors = errors;
    }

    public String getRequestTimeStampUtc() {
        return requestTimeStampUtc;
    }

    public void setRequestTimeStampUtc(String requestTimestamp) {
        this.requestTimeStampUtc = DateUtil.getUTCDate();
    }

    public String getResponseTimeStampUtc() {
        return responseTimeStampUtc;
    }

    public void setResponseTimeStampUtc(String responseTimestamp) {
        this.responseTimeStampUtc = DateUtil.getUTCDate();
    }

}
