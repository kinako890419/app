package com.example.app.repository;

import com.example.app.entity.SamlConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SamlConfigRepository extends JpaRepository<SamlConfigEntity, Long> {
}
