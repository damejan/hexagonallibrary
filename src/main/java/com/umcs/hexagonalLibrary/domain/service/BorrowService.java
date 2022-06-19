package com.umcs.hexagonalLibrary.domain.service;

import com.umcs.hexagonalLibrary.domain.exceptions.*;
import com.umcs.hexagonalLibrary.domain.model.Book;
import com.umcs.hexagonalLibrary.domain.model.BorrowEntry;
import com.umcs.hexagonalLibrary.domain.model.User;
import com.umcs.hexagonalLibrary.domain.port.out.BorrowRepositoryPort;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class BorrowService {
    private final BorrowRepositoryPort borrowRepositoryPort;

    private final BookService bookService;
    private final UserService userService;

    public BorrowService(BorrowRepositoryPort borrowRepositoryPort, BookService bookService, UserService userService) {
        this.borrowRepositoryPort = borrowRepositoryPort;
        this.bookService = bookService;
        this.userService = userService;
    }

    public List<BorrowEntry> getBorrowEntries() {
        return this.borrowRepositoryPort.findAll();
    }

    public BorrowEntry borrowBook(UUID bookId, UUID userId) {

        Book book = bookService.getBookById(bookId);
        if(Objects.isNull(book)) {
            throw new BookNotFoundException("Book not found.");
        }

        BorrowEntry borrowEntry = getBorrowEntryByBookId(bookId);

        if(Objects.nonNull(borrowEntry)) {
            throw new BookAlreadyBorrowedException("This book is already borrowed.");
        }

        User user = userService.getUserById(userId);

        if(Objects.isNull(user)) {
            throw new UserNotFoundException("User not found.");
        }

        return addBorrowEntry(new BorrowEntry(null, book, user));
    }

    public BorrowEntry returnBook(UUID bookId, UUID userId) {
        BorrowEntry borrowEntry = getBorrowEntryByBookId(bookId);

        if(Objects.isNull(borrowEntry)) {
            throw new BookNotBorrowedException("The book has not yet been borrowed or does not exist.");
        }

        if(!borrowEntry.getUser().getId().equals(userId)) {
            throw new PermissionException("this user does not own the book or does not exist.");
        }

        return deleteBorrowEntryById(borrowEntry.getId());
    }

    private BorrowEntry addBorrowEntry(BorrowEntry borrowEntry) {
        return borrowRepositoryPort.addBorrowEntry(borrowEntry);
    }

    private BorrowEntry getBorrowEntryById(UUID id) {
        return this.borrowRepositoryPort.getBorrowEntryById(id);
    }

    private BorrowEntry getBorrowEntryByUserId(UUID userId) {
        return this.borrowRepositoryPort.getBorrowEntryByUserId(userId);
    }


    private BorrowEntry getBorrowEntryByBookId(UUID bookId) {
        return this.borrowRepositoryPort.getBorrowEntryByBookId(bookId);
    }

    private BorrowEntry deleteBorrowEntryById(UUID borrowEntryId) {
        return this.borrowRepositoryPort.deleteBorrowEntryById(borrowEntryId);
    }
}
