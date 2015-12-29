package com.bbtransact.icp.api.shared.bean;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class OperationsBean {

    private String            result;
    private ArrayList<String> errors;
    private List<ContextBean> contexts;
    private String            requestTimeStampUtc;
    private String            responseTimeStampUtc;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<String> getErrors() {
        return errors;
    }

    public void setErrors(ArrayList<String> errors) {
        this.errors = errors;
    }

    public List<ContextBean> getContexts() {
        return contexts;
    }

    public void setContexts(List<ContextBean> contexts) {
        this.contexts = contexts;
    }

    public String getRequestTimeStampUtc() {
        return requestTimeStampUtc;
    }

    public void setRequestTimeStampUtc(String requestTimeStampUtc) {
        this.requestTimeStampUtc = requestTimeStampUtc;
    }

    public String getResponseTimeStampUtc() {
        return responseTimeStampUtc;
    }

    public void setResponseTimeStampUtc(String responseTimeStampUtc) {
        this.responseTimeStampUtc = responseTimeStampUtc;
    }

}
