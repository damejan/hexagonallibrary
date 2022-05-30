package com.umcs.hexagonalLibrary.infrastructure.persistance.inmemory;

import com.umcs.hexagonalLibrary.domain.model.Book;
import com.umcs.hexagonalLibrary.domain.port.out.BookRepositoryPort;

import java.util.*;

public class BookInMemoryAdapter implements BookRepositoryPort {

    private final Map<UUID, Book> store = new HashMap<>();

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Book addBook(Book book) {
        if(Objects.isNull(book.getId())) {
            book.setId(UUID.randomUUID());
        }

        store.put(book.getId(), book);
        return book;
    }

    @Override
    public Book getBookById(UUID id) {
        return store.get(id);
    }

    @Override
    public Book updateBook(UUID id, Book book) {
        return store.replace(id, book);
    }

    @Override
    public Book deleteBookById(UUID id) {
        return store.remove(id);
    }
}
