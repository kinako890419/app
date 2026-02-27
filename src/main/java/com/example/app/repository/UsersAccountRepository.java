package com.example.app.repository;

import com.example.app.entity.UsersAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersAccountRepository extends JpaRepository<UsersAccountEntity, Long> {
    boolean existsByEmail(String email);
    Optional<UsersAccountEntity> findByEmail(String email);
}
