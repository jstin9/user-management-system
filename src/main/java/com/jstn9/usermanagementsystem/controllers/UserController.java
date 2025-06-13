package com.jstn9.usermanagementsystem.controllers;


import com.jstn9.usermanagementsystem.dto.UserRegistrationDto;
import com.jstn9.usermanagementsystem.dto.UserResponseDto;
import com.jstn9.usermanagementsystem.dto.UserUpdateDto;
import com.jstn9.usermanagementsystem.models.User;
import com.jstn9.usermanagementsystem.services.UserService;
import com.jstn9.usermanagementsystem.utils.MapperUtil;
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
        return userService.getUsers().stream()
                .map(u -> MapperUtil.mapToDto(u, UserResponseDto.class))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        UserResponseDto responseDto = MapperUtil.mapToDto(user, UserResponseDto.class);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRegistrationDto userDto) {
        User user = MapperUtil.mapToEntity(userDto, User.class);
        User createdUser = userService.createUser(user);
        UserResponseDto responseDto = MapperUtil.mapToDto(createdUser, UserResponseDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    //TODO
    //UserUpdateDto with only 1 field so its like Patch now, we need to do it like Put, so needs 3 fields in UserUpdateDto
    //Also need to create Patch method

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateDto userDto) {
        User user = MapperUtil.mapToEntity(userDto, User.class);
        User updatedUser = userService.updateUser(id, user);
        UserResponseDto responseDto = MapperUtil.mapToDto(updatedUser, UserResponseDto.class);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}


