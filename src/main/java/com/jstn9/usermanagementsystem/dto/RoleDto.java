package com.jstn9.usermanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleDto {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;
}
