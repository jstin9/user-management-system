package com.jstn9.usermanagementsystem.controllers;

import com.jstn9.usermanagementsystem.dto.RoleDto;
import com.jstn9.usermanagementsystem.models.Role;
import com.jstn9.usermanagementsystem.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<RoleDto> getAllRoles() {
        return roleService.findAllRoles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    @PostMapping
    public ResponseEntity<RoleDto> createRole(@Valid @RequestBody Role role) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(role));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDto> updateRole(@PathVariable Long id, @Valid @RequestBody Role role) {
        return ResponseEntity.ok(roleService.updateRole(id, role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
