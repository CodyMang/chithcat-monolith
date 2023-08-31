package com.codyMang.chitchatmonolith.payload.response;

public class PingResponse {
    boolean onlineStatus = true;

    public PingResponse(boolean onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public boolean isOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(boolean onlineStatus) {
        this.onlineStatus = onlineStatus;
    }
}
