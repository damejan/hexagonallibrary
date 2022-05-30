package com.umcs.hexagonalLibrary.domain.service;

import com.umcs.hexagonalLibrary.domain.model.Book;
import com.umcs.hexagonalLibrary.domain.port.out.BookRepositoryPort;

import java.util.List;
import java.util.UUID;

public class BookService {

    private final BookRepositoryPort bookRepositoryPort;

    public BookService(BookRepositoryPort bookRepositoryPort) {
        this.bookRepositoryPort = bookRepositoryPort;
    }

    public List<Book> getBooks() {
        return this.bookRepositoryPort.findAll();
    }

    public Book addBook(Book book) {
        return bookRepositoryPort.addBook(book);
    }

    public Book getBookById(UUID id) {
        return this.bookRepositoryPort.getBookById(id);
    }

    public Book updateBook(UUID id, Book book) {
        return this.bookRepositoryPort.updateBook(id, book);
    }

    public Book deleteBookById(UUID id) {
        return this.bookRepositoryPort.deleteBookById(id);
    }
}
