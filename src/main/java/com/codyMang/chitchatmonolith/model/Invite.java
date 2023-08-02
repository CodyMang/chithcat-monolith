package com.codyMang.chitchatmonolith.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.Date;

@Entity
public class Invite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String inviteCode;

    @ManyToOne
    private Account creator;

    @ManyToOne
    private ChatRoom chatRoom;

    @Column
    private LocalDateTime creationDate;

    @Column
    private LocalDateTime expirationDate;

    public Invite() {}

    public Invite(Account creator, ChatRoom chatRoom){
        this.inviteCode = generateRandomSequence();
        this.creator = creator;
        this.chatRoom = chatRoom;
        this.chatRoom.addInvite(this);
        this.creationDate = LocalDateTime.now();
        this.expirationDate = this.creationDate.plusWeeks(1);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public Account getCreator() {
        return creator;
    }

    public void setCreator(Account creator) {
        this.creator = creator;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    private String generateRandomSequence(){
        long max = 1_000_000_000;
        long min = 0;

        long random_int = (long) Math.floor(Math.random() * (max - min + 1) + min);

        String res = Long.toHexString(random_int);

        StringBuilder sb = new StringBuilder();

        int n = 8 - res.length();// 8 is the max amount of chars res can be since 1 billion in HEX is 3B9ACA00
        return String.valueOf('0').repeat(n) + res; // Fill left with 0

    }
}
