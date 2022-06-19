package com.umcs.hexagonalLibrary.infrastructure.persistance.inmemory;

import com.umcs.hexagonalLibrary.domain.model.BorrowEntry;
import com.umcs.hexagonalLibrary.domain.port.out.BorrowRepositoryPort;

import java.util.*;

public class BorrowInMemoryAdapter implements BorrowRepositoryPort {

    private final Map<UUID, BorrowEntry> store = new HashMap<>();

    @Override
    public List<BorrowEntry> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public BorrowEntry addBorrowEntry(BorrowEntry borrowEntry) {
        if(Objects.isNull(borrowEntry.getId())) {
            borrowEntry.setId(UUID.randomUUID());
        }
        store.put(borrowEntry.getId(), borrowEntry);

        return borrowEntry;
    }

    @Override
    public BorrowEntry getBorrowEntryById(UUID id) {
        return store.get(id);
    }

    @Override
    public BorrowEntry getBorrowEntryByUserId(UUID id) {
        var borrowEntries = store.values().stream()
                .filter(borrowEntry -> borrowEntry.getUser().getId().equals(id))
                .toList();

        if(borrowEntries.isEmpty()) {
            return null;
        }
        return borrowEntries.get(0);
    }

    @Override
    public BorrowEntry getBorrowEntryByBookId(UUID id) {
        var borrowEntries = store.values().stream()
                .filter(borrowEntry -> borrowEntry.getBook().getId().equals(id))
                .toList();

        if(borrowEntries.isEmpty()) {
            return null;
        }
        return borrowEntries.get(0);
    }

    @Override
    public BorrowEntry deleteBorrowEntryById(UUID borrowEntryId) {
        return store.remove(borrowEntryId);
    }
}
