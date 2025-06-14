package com.jstn9.usermanagementsystem.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, Long id) {
        super(resourceName + " with ID " + id + " not found");
    }
}
