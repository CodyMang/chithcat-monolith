package com.codyMang.chitchatmonolith;

import com.codyMang.chitchatmonolith.model.Account;
import com.codyMang.chitchatmonolith.model.ERole;
import com.codyMang.chitchatmonolith.model.Role;
import com.codyMang.chitchatmonolith.service.AccountRepository;
import com.codyMang.chitchatmonolith.service.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataRunner implements CommandLineRunner{

    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String...args) throws Exception{

        if(roleRepository.findAll().isEmpty()) {
            roleRepository.save(new Role(ERole.ROLE_USER));
            roleRepository.save(new Role(ERole.ROLE_MODERATOR));
            roleRepository.save(new Role(ERole.ROLE_ADMIN));
        }

    }
}
