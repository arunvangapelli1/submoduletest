package com.bbtransact.icp.api.shared.bean;

import org.springframework.stereotype.Component;

@Component
public class IssuerBean {
    private String id;
    private String name;
    private String localeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocaleId() {
        return localeId;
    }

    public void setLocaleId(String localeId) {
        this.localeId = localeId;
    }
}
