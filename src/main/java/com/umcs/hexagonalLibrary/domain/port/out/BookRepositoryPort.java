package com.umcs.hexagonalLibrary.domain.port.out;

import com.umcs.hexagonalLibrary.domain.model.Book;

import java.util.List;
import java.util.UUID;

public interface BookRepositoryPort {
    List<Book> findAll();
    Book addBook(Book book);
    Book getBookById(UUID id);
    Book updateBook(UUID id, Book book);
    Book deleteBookById(UUID id);
}
