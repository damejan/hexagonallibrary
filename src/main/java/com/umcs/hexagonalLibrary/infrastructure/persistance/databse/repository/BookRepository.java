package com.umcs.hexagonalLibrary.infrastructure.persistance.databse.repository;

import com.umcs.hexagonalLibrary.infrastructure.persistance.databse.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<BookEntity, UUID> {
}
