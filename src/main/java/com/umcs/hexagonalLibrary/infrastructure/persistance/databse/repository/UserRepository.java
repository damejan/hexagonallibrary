package com.umcs.hexagonalLibrary.infrastructure.persistance.databse.repository;

import com.umcs.hexagonalLibrary.infrastructure.persistance.databse.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
