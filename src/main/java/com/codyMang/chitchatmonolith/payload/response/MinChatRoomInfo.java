package com.codyMang.chitchatmonolith.payload.response;

import com.codyMang.chitchatmonolith.model.ChatRoom;

import java.time.LocalDateTime;

public class MinChatRoomInfo {
    private Long id;

    private String chatRoomName;

    private LocalDateTime latestInteraction;

    public MinChatRoomInfo(Long id, String chatRoomName, LocalDateTime latestInteraction) {
        this.id = id;
        this.chatRoomName = chatRoomName;
        this.latestInteraction = latestInteraction;
    }

    public MinChatRoomInfo(ChatRoom room) {
        this.id = room.getId();
        this.chatRoomName = room.getChatName();
        this.latestInteraction = room.getLatestInteraction();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChatRoomName() {
        return chatRoomName;
    }

    public void setChatRoomName(String chatRoomName) {
        this.chatRoomName = chatRoomName;
    }

    public LocalDateTime getLatestInteraction() {
        return latestInteraction;
    }

    public void setLatestInteraction(LocalDateTime latestInteraction) {
        this.latestInteraction = latestInteraction;
    }
}
