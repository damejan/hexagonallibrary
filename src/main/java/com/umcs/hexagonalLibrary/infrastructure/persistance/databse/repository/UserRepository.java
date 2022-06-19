package com.umcs.hexagonalLibrary.infrastructure.persistance.databse.repository;

import com.umcs.hexagonalLibrary.infrastructure.persistance.databse.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
