package com.codyMang.chitchatmonolith.controller;

import com.codyMang.chitchatmonolith.model.Account;
import com.codyMang.chitchatmonolith.payload.response.AccountInfoResponse;
import com.codyMang.chitchatmonolith.security.UserDetails.UserDetailsImpl;
import com.codyMang.chitchatmonolith.security.jwt.AuthTokenFilter;
import com.codyMang.chitchatmonolith.service.AccountRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.rmi.ServerException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/account",produces = "application/json")
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    AccountRepository accountRepository;

    @GetMapping
    public ResponseEntity<?> getAccountInfo(Principal principal){
        Authentication authentication = (Authentication) principal;
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        Optional<Account> account = accountRepository.findById(user.getId());
        if(account.isPresent())
            return ResponseEntity.ok(new AccountInfoResponse(account.get()));
        return new ResponseEntity<>("User info Not found, Try Logging in again", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(value="/{accountId}")
    public ResponseEntity<?> getAccount(@PathVariable long accountId){
        Optional<Account> acc = accountRepository.findById(accountId);
        if(acc.isPresent()){
            return ResponseEntity.ok(new AccountInfoResponse(acc.get()));
        }
        return new ResponseEntity<>("This account id could not be found", HttpStatus.NOT_FOUND);
    }


}
