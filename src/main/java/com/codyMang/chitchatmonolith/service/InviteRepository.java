package com.codyMang.chitchatmonolith.service;

import com.codyMang.chitchatmonolith.model.Invite;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InviteRepository extends CrudRepository<Invite,Long>{
    Optional<Invite> findByInviteCode(String Invite);
}
