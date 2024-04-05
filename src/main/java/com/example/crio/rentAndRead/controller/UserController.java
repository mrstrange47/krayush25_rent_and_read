package com.example.crio.rentAndRead.controller;

import com.example.crio.rentAndRead.dto.request.AuthRequest;
import com.example.crio.rentAndRead.dto.request.RegisterRequest;
import com.example.crio.rentAndRead.dto.request.RentBookRequest;
import com.example.crio.rentAndRead.dto.response.AuthResponse;
import com.example.crio.rentAndRead.entity.Book;
import com.example.crio.rentAndRead.entity.User;
import com.example.crio.rentAndRead.exception.BookNotFoundException;
import com.example.crio.rentAndRead.exception.BookUnavailableException;
import com.example.crio.rentAndRead.exception.UserNotFoundException;
import com.example.crio.rentAndRead.service.AuthService;
import com.example.crio.rentAndRead.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    // GET /users
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }

    // GET /users/{userId}
    @GetMapping("/userId")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getUserById(@PathVariable Long userId) throws UserNotFoundException {
        User user = null;
        try{
            user = userService.getUserById(userId);
        }
        catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(user);
    }
}
