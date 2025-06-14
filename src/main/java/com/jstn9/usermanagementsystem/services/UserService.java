package com.jstn9.usermanagementsystem.services;

import com.jstn9.usermanagementsystem.dto.UserPartialUpdateDto;
import com.jstn9.usermanagementsystem.dto.UserRegistrationDto;
import com.jstn9.usermanagementsystem.dto.UserResponseDto;
import com.jstn9.usermanagementsystem.dto.UserUpdateDto;
import com.jstn9.usermanagementsystem.exceptions.ResourceNotFoundException;
import com.jstn9.usermanagementsystem.models.User;
import com.jstn9.usermanagementsystem.repositories.UserRepository;
import com.jstn9.usermanagementsystem.utils.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDto> getUsers() {
        return userRepository.findAll().stream()
                .map(u -> MapperUtil.mapToDto(u, UserResponseDto.class))
                .toList();
    }

    public UserResponseDto getUserById(Long id) {
        User user = findUserOrThrow(id);
        return MapperUtil.mapToDto(user, UserResponseDto.class);
    }

    public UserResponseDto createUser(UserRegistrationDto userDto) {
        User user = MapperUtil.mapToEntity(userDto, User.class);
        User createdUser = userRepository.save(user);
        return MapperUtil.mapToDto(createdUser, UserResponseDto.class);
    }

    public UserResponseDto updateUser(Long id, UserUpdateDto userDto) {
        User user = findUserOrThrow(id);

        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());

        User updatedUser = userRepository.save(user);
        return MapperUtil.mapToDto(updatedUser, UserResponseDto.class);
    }

    public UserResponseDto partialUpdateUser(Long id, UserPartialUpdateDto userDto) {
        User foundUser = findUserOrThrow(id);

        if(userDto.getEmail() != null) {
            foundUser.setEmail(userDto.getEmail());
        }
        if(userDto.getUsername() != null) {
            foundUser.setUsername(userDto.getUsername());
        }
        if(userDto.getPassword() != null) {
            foundUser.setPassword(userDto.getPassword());
        }

        return MapperUtil.mapToDto(userRepository.save(foundUser), UserResponseDto.class);
    }

    public void deleteUser(Long id) {
        findUserOrThrow(id);
        userRepository.deleteById(id);
    }

    private User findUserOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
    }

}
