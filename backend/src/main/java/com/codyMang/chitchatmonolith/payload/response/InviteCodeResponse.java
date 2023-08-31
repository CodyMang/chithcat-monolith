package com.codyMang.chitchatmonolith.payload.response;

import com.codyMang.chitchatmonolith.model.Invite;

public class InviteCodeResponse {

    String invite_url;

    public InviteCodeResponse(Invite invite){
        this.invite_url = String.format("/chat/i/%s",invite.getInviteCode());
    }

    public void setInvite_url(String invite_url) {
        this.invite_url = invite_url;
    }

    public String getInvite_url() {
        return invite_url;
    }

}
