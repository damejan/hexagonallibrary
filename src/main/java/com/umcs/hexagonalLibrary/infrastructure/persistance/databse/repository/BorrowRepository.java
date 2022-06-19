package com.umcs.hexagonalLibrary.infrastructure.persistance.databse.repository;

import com.umcs.hexagonalLibrary.infrastructure.persistance.databse.entity.BorrowEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BorrowRepository extends JpaRepository<BorrowEntryEntity, UUID> {

    Optional<BorrowEntryEntity> findByBookEntity_id(UUID bookId);
    Optional<BorrowEntryEntity> findByUserEntity_id(UUID userId);
}
