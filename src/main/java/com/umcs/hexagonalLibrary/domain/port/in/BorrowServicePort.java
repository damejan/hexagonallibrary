package com.umcs.hexagonalLibrary.domain.port.in;

import com.umcs.hexagonalLibrary.domain.model.BorrowEntry;

import java.util.List;
import java.util.UUID;

public interface BorrowServicePort {
    List<BorrowEntry> getBorrowEntries();
    BorrowEntry borrowBook(UUID bookId, UUID userId);
    BorrowEntry returnBook(UUID bookId, UUID userId);
}
