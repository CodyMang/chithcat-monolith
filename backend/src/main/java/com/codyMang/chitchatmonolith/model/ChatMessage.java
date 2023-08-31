package com.codyMang.chitchatmonolith.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Embeddable
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "account_id", insertable = false, updatable = false)
    private Long senderId;

    @ManyToOne
    private ChatRoom chatRoom;

    @Column(nullable = false)
    private LocalDateTime publishedDateTime;

    public ChatMessage() {
    }

    public ChatMessage(String content, Account account, ChatRoom chatRoom) {
        this.content = content;
        this.account = account;
        this.senderId = account.getId();
        this.chatRoom = chatRoom;
        this.publishedDateTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long user) {
        this.senderId = user;
    }

    public ChatRoom getChatID() {
        return chatRoom;
    }

    public void setChatID(ChatRoom chatRoomID) {
        this.chatRoom = chatRoomID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getPublishedOn() {
        return publishedDateTime;
    }

    public void setPublishedOn(LocalDateTime publishedOn) {
        this.publishedDateTime = publishedOn;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}