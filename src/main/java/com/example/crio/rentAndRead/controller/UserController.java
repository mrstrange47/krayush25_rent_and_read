package com.example.crio.rentAndRead.controller;

import com.example.crio.rentAndRead.dto.request.AuthRequest;
import com.example.crio.rentAndRead.dto.request.RegisterRequest;
import com.example.crio.rentAndRead.dto.response.AuthResponse;
import com.example.crio.rentAndRead.entity.User;
import com.example.crio.rentAndRead.service.AuthService;
import com.example.crio.rentAndRead.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> getAllUsers(){
        return null;
    }
}
