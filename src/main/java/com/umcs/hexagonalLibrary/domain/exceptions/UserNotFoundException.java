package com.umcs.hexagonalLibrary.domain.exceptions;

public class UserNotFoundException extends LibraryDomainException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
