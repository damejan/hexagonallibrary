package com.umcs.hexagonalLibrary.infrastructure.application.adapter;

import com.umcs.hexagonalLibrary.domain.model.Book;
import com.umcs.hexagonalLibrary.domain.port.in.BookServicePort;
import com.umcs.hexagonalLibrary.domain.service.BookService;

import java.util.List;
import java.util.UUID;

public class BookServiceAdapter implements BookServicePort {

    private final BookService bookService;

    public BookServiceAdapter(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @Override
    public Book addBook(Book book) {
        return bookService.addBook(book);
    }

    @Override
    public Book getBookById(UUID id) {
        return bookService.getBookById(id);
    }

    @Override
    public Book updateBook(UUID id, Book book) {
        return bookService.updateBook(id, book);
    }

    @Override
    public Book deleteBookById(UUID id) {
        return bookService.deleteBookById(id);
    }
}
