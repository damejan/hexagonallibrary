package com.umcs.hexagonalLibrary.domain.model;

import java.util.UUID;

public class BorrowEntry {

    private UUID id;
    private Book book;
    private User user;

    public BorrowEntry(UUID id, Book book, User user) {
        this.id = id;
        this.book = book;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
