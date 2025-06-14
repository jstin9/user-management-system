package com.jstn9.usermanagementsystem.repositories;

import com.jstn9.usermanagementsystem.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
