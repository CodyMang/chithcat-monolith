package com.codyMang.chitchatmonolith.service;

import com.codyMang.chitchatmonolith.model.Account;
import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account,Long> {
    Optional<Account> findById(long id);
    Optional<Account> findByUsername(String username);
    boolean existsByUsername(String username);
    ArrayList<Account> findAll();
}
