package com.umcs.hexagonalLibrary.domain.port.in;

import com.umcs.hexagonalLibrary.domain.model.Book;

import java.util.List;
import java.util.UUID;

public interface BookServicePort {
    List<Book> getBooks();
    Book addBook(Book book);
    Book getBookById(UUID id);
    Book updateBook(UUID id, Book book);
    Book deleteBookById(UUID id);
}
