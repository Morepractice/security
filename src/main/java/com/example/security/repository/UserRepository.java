package com.example.security.repository;

import com.example.security.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 22139
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
}
