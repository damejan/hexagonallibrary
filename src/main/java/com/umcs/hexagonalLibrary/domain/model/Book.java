package com.umcs.hexagonalLibrary.domain.model;

import java.util.UUID;

public class Book {
    private UUID id;
    private String author;
    private String title;

    public Book(UUID id, String author, String title) {
        this.id = id;
        this.author = author;
        this.title = title;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
