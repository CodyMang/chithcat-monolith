package com.codyMang.chitchatmonolith.service;

import com.codyMang.chitchatmonolith.model.ChatRoom;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ChatRepository extends CrudRepository<ChatRoom,Long> {
    Optional<ChatRoom> findById(long id);
}
