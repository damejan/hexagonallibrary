package com.umcs.hexagonalLibrary.domain.port.out;

import com.umcs.hexagonalLibrary.domain.model.BorrowEntry;

import java.util.List;
import java.util.UUID;

public interface BorrowRepositoryPort {
    List<BorrowEntry> findAll();
    BorrowEntry addBorrowEntry(BorrowEntry borrowEntry);
    BorrowEntry getBorrowEntryById(UUID id);

    BorrowEntry getBorrowEntryByUserId(UUID id);
    BorrowEntry getBorrowEntryByBookId(UUID id);

    BorrowEntry deleteBorrowEntryById(UUID borrowEntryId);
}
