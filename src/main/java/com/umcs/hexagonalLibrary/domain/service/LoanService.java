package com.umcs.hexagonalLibrary.domain.service;

import com.umcs.hexagonalLibrary.domain.exceptions.*;
import com.umcs.hexagonalLibrary.domain.model.Book;
import com.umcs.hexagonalLibrary.domain.model.Loan;
import com.umcs.hexagonalLibrary.domain.model.User;
import com.umcs.hexagonalLibrary.domain.port.out.LoanRepositoryPort;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class LoanService {
    private final LoanRepositoryPort loanRepositoryPort;

    private final BookService bookService;
    private final UserService userService;

    public LoanService(LoanRepositoryPort loanRepositoryPort, BookService bookService, UserService userService) {
        this.loanRepositoryPort = loanRepositoryPort;
        this.bookService = bookService;
        this.userService = userService;
    }

    public List<Loan> getLoans() {
        return this.loanRepositoryPort.findAll();
    }

    public Loan addLoan(Loan loan) {
        return loanRepositoryPort.addLoan(loan);
    }

    public Loan getLoanById(UUID id) {
        return this.loanRepositoryPort.getLoanById(id);
    }

    public Loan getLoanByUserId(UUID userId) {
        return this.loanRepositoryPort.getLoanByUserId(userId);
    }


    public Loan getLoanByBookId(UUID bookId) {
        return this.loanRepositoryPort.getLoanByBookId(bookId);
    }

    public Loan deleteLoanById(UUID loanId) {
        return this.loanRepositoryPort.deleteLoanById(loanId);
    }

    public Loan borrowBook(UUID bookId, UUID userId) {
        Loan loan = getLoanByBookId(bookId);

        if(Objects.nonNull(loan)) {
            throw new BookAlreadyBorrowedException("This book is already borrowed.");
        }

        Book book = bookService.getBookById(bookId);

        if(Objects.isNull(book)) {
            throw new BookNotFoundException("Book not found.");
        }

        User user = userService.getUserById(userId);

        if(Objects.isNull(user)) {
            throw new UserNotFoundException("User not found.");
        }

        return addLoan(new Loan(null, book, user));
    }

    public Loan returnBook(UUID bookId, UUID userId) {
        Loan loan = getLoanByBookId(bookId);

        if(Objects.isNull(loan)) {
            throw new BookNotBorrowedException("The book has not yet been borrowed or does not exist.");
        }

        if(!loan.getUser().getId().equals(userId)) {
            throw new PermissionException("this user does not own the book.");
        }

        return deleteLoanById(loan.getId());
    }

}