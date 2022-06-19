package com.umcs.hexagonalLibrary.infrastructure.application.adapter;

import com.umcs.hexagonalLibrary.domain.model.BorrowEntry;
import com.umcs.hexagonalLibrary.domain.port.in.BorrowServicePort;
import com.umcs.hexagonalLibrary.domain.service.BorrowService;

import java.util.List;
import java.util.UUID;

public class BorrowServiceAdapter implements BorrowServicePort {

    private final BorrowService borrowService;

    public BorrowServiceAdapter(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @Override
    public List<BorrowEntry> getBorrowEntries() {
        return borrowService.getBorrowEntries();
    }

    @Override
    public BorrowEntry borrowBook(UUID bookId, UUID userId) {
        return borrowService.borrowBook(bookId, userId);
    }

    @Override
    public BorrowEntry returnBook(UUID bookId, UUID userId) {
        return borrowService.returnBook(bookId, userId);
    }
}
