
package com.bbtransact.icp.api.services.bean.v1;

import org.springframework.stereotype.Component;

@Component
public class RequestVO {

    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
