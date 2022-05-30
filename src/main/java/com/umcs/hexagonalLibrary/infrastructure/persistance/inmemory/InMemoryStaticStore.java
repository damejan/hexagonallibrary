package com.umcs.hexagonalLibrary.infrastructure.persistance.inmemory;

import com.umcs.hexagonalLibrary.domain.model.Book;
import com.umcs.hexagonalLibrary.domain.model.Loan;
import com.umcs.hexagonalLibrary.domain.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryStaticStore {

    public static final Map<UUID, Book> books = new HashMap<>();
    public static final Map<UUID, User> users = new HashMap<>();
    public static final Map<UUID, Loan> loans = new HashMap<>();
}
