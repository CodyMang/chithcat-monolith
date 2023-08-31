package com.codyMang.chitchatmonolith.service;

import com.codyMang.chitchatmonolith.model.ChatMessage;
import com.codyMang.chitchatmonolith.model.ChatRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;



public interface ChatMessageRepository extends CrudRepository<ChatMessage,Long>, PagingAndSortingRepository<ChatMessage,Long> {
    //Pageable sortedByMessagePublishedDateTime = PageRequest.of(0, 3, Sort.by("price").descending());

    Page<ChatMessage> findByChatRoom(ChatRoom chatRoom, Pageable pageable);
}
