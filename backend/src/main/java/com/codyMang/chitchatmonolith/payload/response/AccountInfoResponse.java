package com.codyMang.chitchatmonolith.payload.response;

import com.codyMang.chitchatmonolith.model.Account;
import org.apache.catalina.Role;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class AccountInfoResponse {
    private Long id;
    private String username;
    private List<String> roles;
    private List<MinChatRoomInfo> chatRoomList;


//    public AccountInfoResponse(Long id, String username, List<String> roles) {
//        this.id = id;
//        this.username = username;
//        this.roles = roles;
//    }

    public AccountInfoResponse(Account acc) {
        this.id = acc.getId();
        this.username = acc.getUsername();
        this.roles = acc.getRoles().stream().map(role -> role.getName().toString())
                .toList();
        this.chatRoomList = acc.getChatRooms().stream().map(MinChatRoomInfo::new).toList();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<MinChatRoomInfo> getChatRoomList() {
        return chatRoomList;
    }

    public void setChatRoomList(List<MinChatRoomInfo> chatRoomList) {
        this.chatRoomList = chatRoomList;
    }
}
