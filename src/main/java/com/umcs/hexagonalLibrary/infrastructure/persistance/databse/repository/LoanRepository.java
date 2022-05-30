package com.umcs.hexagonalLibrary.infrastructure.persistance.databse.repository;

import com.umcs.hexagonalLibrary.infrastructure.persistance.databse.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LoanRepository extends JpaRepository<LoanEntity, UUID> {

    Optional<LoanEntity> findByBookEntity_id(UUID bookId);
    Optional<LoanEntity> findByUserEntity_id(UUID userId);
}
