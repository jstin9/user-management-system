package com.jstn9.usermanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PermissionDto {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;
}
