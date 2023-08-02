package com.codyMang.chitchatmonolith.model;

import jakarta.persistence.*;


import java.time.LocalDateTime;
import java.util.*;

@Entity
@Embeddable
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String chatName;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account owner;

    private LocalDateTime creationDate;

    private LocalDateTime latestInteraction;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "account_chat_rooms",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id"))
    private Set<Account> users = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY) // Only list messages on getMessages()
    @OrderBy("publishedDateTime DESC")
    private List<ChatMessage> messages = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Invite> invites = new HashSet<>();
    public ChatRoom(){}

    public ChatRoom(String chatName, Account owner) {
        this.chatName = chatName;
        this.owner = owner;
        this.creationDate = LocalDateTime.now();
        this.latestInteraction = this.creationDate;
        this.addAccount(owner);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public int getSize() {
        return users.size();
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Set<Account> getUsers() {
        return users;
    }

    public void setUsers(Set<Account> users) {
        this.users = users;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public LocalDateTime getLatestInteraction() {
        return latestInteraction;
    }

    public void setLatestInteraction(LocalDateTime latestInteraction) {
        this.latestInteraction = latestInteraction;
    }


    public void addAccount(Account account){
        this.getUsers().add(account);
        account.addChatRoom(this);
    }

    public void addInvite(Invite invite){
        invites.add(invite);
    }

    public void addMessage(ChatMessage message){
        if(message.getPublishedOn().isAfter(this.latestInteraction))
        {
            latestInteraction = message.getPublishedOn();
        }
        messages.add(message);
    }
}