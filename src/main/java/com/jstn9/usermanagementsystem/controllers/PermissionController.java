package com.jstn9.usermanagementsystem.controllers;


import com.jstn9.usermanagementsystem.dto.PermissionDto;
import com.jstn9.usermanagementsystem.models.Permission;
import com.jstn9.usermanagementsystem.services.PermissionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {
    
    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping
    public List<PermissionDto> getAllPermissions() {
        return permissionService.findAllPermissions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionDto> getPermissionById(@PathVariable Long id) {
        return ResponseEntity.ok(permissionService.getPermissionById(id));
    }

    @PostMapping
    public ResponseEntity<PermissionDto> createPermission(@Valid @RequestBody Permission permission) {
        return ResponseEntity.status(HttpStatus.CREATED).body(permissionService.createPermission(permission));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissionDto> updatePermission(@PathVariable Long id, @Valid @RequestBody Permission permission) {
        return ResponseEntity.ok(permissionService.updatePermission(id, permission));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermission(@PathVariable Long id) {
        permissionService.deletePermission(id);
        return ResponseEntity.noContent().build();
    }
}
