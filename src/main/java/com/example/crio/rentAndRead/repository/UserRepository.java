package com.example.crio.rentAndRead.repository;

import com.example.crio.rentAndRead.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
