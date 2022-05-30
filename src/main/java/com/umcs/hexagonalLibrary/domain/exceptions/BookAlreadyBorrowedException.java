package com.umcs.hexagonalLibrary.domain.exceptions;

public class BookAlreadyBorrowedException extends LibraryDomainException {
    public BookAlreadyBorrowedException(String message) {
        super(message);
    }
}
