package com.codyMang.chitchatmonolith.controller;


import com.codyMang.chitchatmonolith.payload.response.PingResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path="/p", produces = "application/json")
public class TestController {
    // Made for testing authorities of user or as a ping service

    @GetMapping
    public ResponseEntity<?> testOnlineStatus(){
        return ResponseEntity.ok( new PingResponse(true));
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MOD') or hasRole('ADMIN')")
    public ResponseEntity<?> testUserAuth(){
        return ResponseEntity.ok( new PingResponse(true));
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MOD') or hasRole('ADMIN')")
    public ResponseEntity<?> testModAuth(){
        return ResponseEntity.ok( new PingResponse(true));
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> testAdminAuth(){
        return ResponseEntity.ok( new PingResponse(true));
    }
}
