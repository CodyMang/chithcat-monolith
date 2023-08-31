package com.codyMang.chitchatmonolith.payload.response;

import com.codyMang.chitchatmonolith.model.ChatMessage;

import java.time.LocalDateTime;

public class ChatMessageInfo{
    Long senderId;
    String content;

    LocalDateTime publishedDateTime;

    public ChatMessageInfo(ChatMessage chatMessage) {
        this.senderId = chatMessage.getSenderId();
        this.content = chatMessage.getContent();
        this.publishedDateTime = chatMessage.getPublishedOn();
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getPublishedDateTime() {
        return publishedDateTime;
    }

    public void setPublishedDateTime(LocalDateTime publishedDateTime) {
        this.publishedDateTime = publishedDateTime;
    }

}
