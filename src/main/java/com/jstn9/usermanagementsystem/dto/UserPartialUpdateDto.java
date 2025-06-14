package com.jstn9.usermanagementsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserPartialUpdateDto {

    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
    private String username;

    @Email(message = "Email should be valid")
    private String email;

    @Size(min = 5, message = "Password must be at least 5 characters")
    private String password;
}
