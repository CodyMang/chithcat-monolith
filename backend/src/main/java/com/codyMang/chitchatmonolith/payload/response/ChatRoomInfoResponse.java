package com.codyMang.chitchatmonolith.payload.response;

import com.codyMang.chitchatmonolith.model.Account;
import com.codyMang.chitchatmonolith.model.ChatRoom;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class ChatRoomInfoResponse extends MinChatRoomInfo{

    private String owner;
    private Map<Long, String> userList;
    private List<ChatMessageInfo> messages;

    public ChatRoomInfoResponse(ChatRoom chatRoom) {
        super(chatRoom.getId(),chatRoom.getChatName(),chatRoom.getLatestInteraction());
        this.owner = chatRoom.getOwner().getUsername();
        this.userList = chatRoom.getUsers().stream().collect(Collectors.toMap(Account::getId, Account::getUsername));
        this.messages = chatRoom.getMessages().stream().map(ChatMessageInfo::new).toList();
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Map<Long, String> getUserList() {
        return userList;
    }

    public void setUserList(Map<Long, String> userList) {
        this.userList = userList;
    }

    public List<ChatMessageInfo> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessageInfo> messages) {
        this.messages = messages;
    }

}
