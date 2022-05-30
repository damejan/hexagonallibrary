package com.umcs.hexagonalLibrary.domain.exceptions;

public class BookNotBorrowedException extends LibraryDomainException{
    public BookNotBorrowedException(String message) {
        super(message);
    }
}
