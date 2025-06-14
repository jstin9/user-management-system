package com.jstn9.usermanagementsystem.controllers;


import com.jstn9.usermanagementsystem.dto.UserRegistrationDto;
import com.jstn9.usermanagementsystem.dto.UserUpdateDto;
import com.jstn9.usermanagementsystem.dto.UserResponseDto;
import com.jstn9.usermanagementsystem.dto.UserPartialUpdateDto;
import com.jstn9.usermanagementsystem.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRegistrationDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateDto userDto) {
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> partialUpdateUser(@PathVariable Long id, @Valid @RequestBody UserPartialUpdateDto userDto) {
        return ResponseEntity.ok(userService.partialUpdateUser(id, userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}


