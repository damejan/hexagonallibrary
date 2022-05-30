package com.umcs.hexagonalLibrary.domain.exceptions;

public class BookNotFoundException extends LibraryDomainException {

    public BookNotFoundException(String message) {
        super(message);
    }
}
