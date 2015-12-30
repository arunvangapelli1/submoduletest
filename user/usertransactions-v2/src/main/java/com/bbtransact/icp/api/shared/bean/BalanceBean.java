package com.bbtransact.icp.api.shared.bean;

import org.springframework.stereotype.Component;

@Component
public class BalanceBean {

    private double actual;
    private double available;

    public double getActual() {
        return actual;
    }

    public void setActual(double actual) {
        this.actual = actual;
    }

    public double getAvailable() {
        return available;
    }

    public void setAvailable(double available) {
        this.available = available;
    }

}
