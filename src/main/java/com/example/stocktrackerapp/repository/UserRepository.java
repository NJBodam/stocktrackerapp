package com.example.stocktrackerapp.repository;

import com.example.stocktrackerapp.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByEmailAndPassword(String email, String password);
    Optional<UserInfo> findByEmail(String email);
}
