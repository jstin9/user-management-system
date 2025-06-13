package com.jstn9.usermanagementsystem.repositories;

import com.jstn9.usermanagementsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
