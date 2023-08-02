package com.codyMang.chitchatmonolith.controller;


import com.codyMang.chitchatmonolith.model.Account;
import com.codyMang.chitchatmonolith.model.ChatMessage;
import com.codyMang.chitchatmonolith.model.ChatRoom;
import com.codyMang.chitchatmonolith.model.Invite;
import com.codyMang.chitchatmonolith.payload.request.ChatMessageSendRequest;
import com.codyMang.chitchatmonolith.payload.request.ChatRoomCreateRequest;
import com.codyMang.chitchatmonolith.payload.response.ChatRoomInfoResponse;
import com.codyMang.chitchatmonolith.payload.response.InviteCodeResponse;
import com.codyMang.chitchatmonolith.security.UserDetails.UserDetailsImpl;
import com.codyMang.chitchatmonolith.security.jwt.JwtUtils;
import com.codyMang.chitchatmonolith.service.AccountRepository;
import com.codyMang.chitchatmonolith.service.ChatMessageRepository;
import com.codyMang.chitchatmonolith.service.ChatRepository;
import com.codyMang.chitchatmonolith.service.InviteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping(path="/chat",produces = "application/json")
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private ChatRepository chatRepo;

    @Autowired
    private InviteRepository inviteRepo;

    @Autowired
    private ChatMessageRepository chatMessageRepo;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping
    public ResponseEntity<?> createChatRoom(Principal principal, @RequestBody ChatRoomCreateRequest request){
        Authentication authentication = (Authentication) principal;
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        Optional<Account> owner = accountRepo.findById(user.getId());
        if(owner.isPresent()) {
            ChatRoom chatRoom = new ChatRoom(request.getChatName(),owner.get());
            chatRoom = chatRepo.save(chatRoom);
            logger.debug("Created New Chat {}",chatRoom.toString());
            return ResponseEntity.ok(new ChatRoomInfoResponse(chatRoom));
        }
        return new ResponseEntity<>("Could not Validate", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<?> getChatInfo(Principal principal, @PathVariable long chatId){
        Optional<ChatRoom> chatroom = chatRepo.findById(chatId);
        if(chatroom.isPresent()){
            return ResponseEntity.ok(new ChatRoomInfoResponse(chatroom.get()));
        }
        return new ResponseEntity<>("Could not find chat with that ID", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/invite/{chatId}")
    public ResponseEntity<?> createNewInvite(Principal principal, @PathVariable long chatId){
        Authentication authentication = (Authentication) principal;
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        Optional<Account> owner = accountRepo.findById(user.getId());
        Optional<ChatRoom> chatRoom = chatRepo.findById(chatId);

        if(owner.isPresent() && chatRoom.isPresent() && chatRoom.get().getUsers().contains(owner.get())) {
            Invite newInvite = new Invite(owner.get(),chatRoom.get());
            inviteRepo.save(newInvite);
            return ResponseEntity.ok(new InviteCodeResponse(newInvite));
        }
        return new ResponseEntity<>("Could not find chat with that id", HttpStatus.UNAUTHORIZED);

    }

    @PostMapping("/i/{inviteCode}")
    public ResponseEntity<?> joinChatFromInvite(Principal principal, @PathVariable String inviteCode){
        Authentication authentication = (Authentication) principal;
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        Optional<Invite> invite = inviteRepo.findByInviteCode(inviteCode);
        Optional<Account> account = accountRepo.findById(user.getId());
        if(invite.isPresent() && account.isPresent()){
            invite.get().getChatRoom().addAccount(account.get());

            chatRepo.save(invite.get().getChatRoom());
            logger.info("Chat Users: {}",invite.get().getChatRoom().getUsers());

            return new ResponseEntity<>(String.format("%s Joined chat",account.get().getUsername()), HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Invalid invite code", HttpStatus.UNAUTHORIZED);

    }

    @PostMapping("/m/{chatId}")
    public ResponseEntity<?> sendMessageToChat(Principal principal,
                                               @PathVariable long chatId,
                                               @RequestBody ChatMessageSendRequest chatMessage){
        Authentication authentication = (Authentication) principal;
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        Optional<ChatRoom> chatRoom = chatRepo.findById(chatId);
        Optional<Account> account = accountRepo.findById(user.getId());

        if(chatRoom.isPresent()
                && account.isPresent()
                && chatRoom.get().getUsers().contains(account.get())) {
            ChatMessage newMessage = new ChatMessage(chatMessage.getContent(), account.get(),chatRoom.get());
            newMessage = chatMessageRepo.save(newMessage);
            chatRoom.get().addMessage(newMessage);
            chatRepo.save(chatRoom.get());
            return new ResponseEntity<>("Message Created", HttpStatus.CREATED);
        }

        if(chatRoom.isEmpty())
            return new ResponseEntity<>("Could not find ChatRoom with given ID ", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>("User is not part of chat ", HttpStatus.UNAUTHORIZED);
    }
}
