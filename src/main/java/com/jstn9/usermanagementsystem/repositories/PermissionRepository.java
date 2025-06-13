package com.jstn9.usermanagementsystem.repositories;

import com.jstn9.usermanagementsystem.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
