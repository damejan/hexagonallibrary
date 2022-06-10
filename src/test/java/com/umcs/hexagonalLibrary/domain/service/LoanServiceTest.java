package com.umcs.hexagonalLibrary.domain.service;

import com.umcs.hexagonalLibrary.domain.exceptions.*;
import com.umcs.hexagonalLibrary.domain.model.Book;
import com.umcs.hexagonalLibrary.domain.model.Loan;
import com.umcs.hexagonalLibrary.domain.model.User;
import com.umcs.hexagonalLibrary.domain.port.out.BookRepositoryPort;
import com.umcs.hexagonalLibrary.domain.port.out.LoanRepositoryPort;
import com.umcs.hexagonalLibrary.domain.port.out.UserRepositoryPort;
import com.umcs.hexagonalLibrary.infrastructure.persistance.inmemory.BookInMemoryAdapter;
import com.umcs.hexagonalLibrary.infrastructure.persistance.inmemory.LoanInMemoryAdapter;
import com.umcs.hexagonalLibrary.infrastructure.persistance.inmemory.UserInMemoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class LoanServiceTest {

    private BookRepositoryPort bookRepositoryPort;
    private UserRepositoryPort userRepositoryPort;
    private LoanRepositoryPort loanRepositoryPort;
    private LoanService loanService;

    @BeforeEach
    void setup() {
        bookRepositoryPort = new BookInMemoryAdapter();
        userRepositoryPort = new UserInMemoryAdapter();
        loanRepositoryPort = new LoanInMemoryAdapter();

        BookService bookService = new BookService(bookRepositoryPort);
        UserService userService = new UserService(userRepositoryPort);

        loanService = new LoanService(loanRepositoryPort, bookService, userService);
    }


    @Test
    void borrowBook() {
        Book testBook = new Book(
                UUID.fromString("0000-00-00-00-000000"),
                "test_author",
                "test_title"
        );
        User testUser = new User(
                UUID.fromString("0000-00-00-00-000001"),
                "jan",
                "kowalksi",
                "test",
                "testPass"
        );

        bookRepositoryPort.addBook(testBook);
        userRepositoryPort.addUSer(testUser);

        loanService.borrowBook(
                UUID.fromString("0000-00-00-00-000000"),
                UUID.fromString("0000-00-00-00-000001")
        );

        List<Loan> loans = loanRepositoryPort.findAll();
        assertEquals(1, loans.size());
        assertEquals(testBook.getId(), loans.get(0).getBook().getId());
        assertEquals(testBook.getAuthor(), loans.get(0).getBook().getAuthor());
        assertEquals(testBook.getTitle(), loans.get(0).getBook().getTitle());
        assertEquals(testUser.getId(), loans.get(0).getUser().getId());
        assertEquals(testUser.getFirstName(), loans.get(0).getUser().getFirstName());
        assertEquals(testUser.getLastName(), loans.get(0).getUser().getLastName());
        assertEquals(testUser.getLogin(), loans.get(0).getUser().getLogin());
        assertEquals(testUser.getPassword(), loans.get(0).getUser().getPassword());
    }

    @Test
    void borrowBookWhenBookNotExist() {
        User testUser = new User(
                UUID.fromString("0000-00-00-00-000001"),
                "jan",
                "kowalksi",
                "test",
                "testPass"
        );

        userRepositoryPort.addUSer(testUser);

        Exception exception = assertThrows(BookNotFoundException.class, () -> {
            loanService.borrowBook(
                    UUID.fromString("0000-00-00-00-000000"),
                    UUID.fromString("0000-00-00-00-000001")
            );
        });

        assertEquals("Book not found.", exception.getMessage());
    }

    @Test
    void borrowBookWhenBookIsAlreadyBorrowed() {
        Book testBook = new Book(
                UUID.fromString("0000-00-00-00-000000"),
                "test_author",
                "test_title"
        );
        User testUser = new User(
                UUID.fromString("0000-00-00-00-000001"),
                "jan",
                "kowalksi",
                "test",
                "testPass"
        );

        bookRepositoryPort.addBook(testBook);
        userRepositoryPort.addUSer(testUser);

        loanService.borrowBook(
                UUID.fromString("0000-00-00-00-000000"),
                UUID.fromString("0000-00-00-00-000001")
        );

        Exception exception = assertThrows(BookAlreadyBorrowedException.class, () -> {
            loanService.borrowBook(
                    UUID.fromString("0000-00-00-00-000000"),
                    UUID.fromString("0000-00-00-00-000001")
            );
        });

        assertEquals("This book is already borrowed.", exception.getMessage());
    }

    @Test
    void borrowBookWhenUserNotExist() {
        Book testBook = new Book(
                UUID.fromString("0000-00-00-00-000000"),
                "test_author",
                "test_title"
        );

        bookRepositoryPort.addBook(testBook);

        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            loanService.borrowBook(
                    UUID.fromString("0000-00-00-00-000000"),
                    UUID.fromString("0000-00-00-00-000001")
            );
        });

        assertEquals("User not found.", exception.getMessage());
    }

    @Test
    void returnBook() {
        Book testBook = new Book(
                UUID.fromString("0000-00-00-00-000000"),
                "test_author",
                "test_title"
        );
        User testUser = new User(
                UUID.fromString("0000-00-00-00-000001"),
                "jan",
                "kowalksi",
                "test",
                "testPass"
        );

        bookRepositoryPort.addBook(testBook);
        userRepositoryPort.addUSer(testUser);

        loanService.borrowBook(
                UUID.fromString("0000-00-00-00-000000"),
                UUID.fromString("0000-00-00-00-000001")
        );

        Loan returnedLoan = loanService.returnBook(
                UUID.fromString("0000-00-00-00-000000"),
                UUID.fromString("0000-00-00-00-000001")
        );
        List<Loan> allLoans = loanRepositoryPort.findAll();

        assertEquals(0, allLoans.size());
        assertEquals(testBook.getId(), returnedLoan.getBook().getId());
        assertEquals(testBook.getAuthor(), returnedLoan.getBook().getAuthor());
        assertEquals(testBook.getTitle(), returnedLoan.getBook().getTitle());
        assertEquals(testUser.getId(), returnedLoan.getUser().getId());
        assertEquals(testUser.getFirstName(), returnedLoan.getUser().getFirstName());
        assertEquals(testUser.getLastName(), returnedLoan.getUser().getLastName());
        assertEquals(testUser.getLogin(), returnedLoan.getUser().getLogin());
        assertEquals(testUser.getPassword(), returnedLoan.getUser().getPassword());
    }

    @Test
    void returnBookWhenBookNotBorrowedYetOrDoesNotExist() {
        Exception exception = assertThrows(BookNotBorrowedException.class, () -> {
            loanService.returnBook(
                    UUID.fromString("0000-00-00-00-000000"),
                    UUID.fromString("0000-00-00-00-000001")
            );
        });

        assertEquals("The book has not yet been borrowed or does not exist.", exception.getMessage());
    }

    @Test
    void returnBookWhenUserDoesNotOwnBookOrDoesNotExist() {
        Book testBook = new Book(
                UUID.fromString("0000-00-00-00-000000"),
                "test_author",
                "test_title"
        );
        User testUser = new User(
                UUID.fromString("0000-00-00-00-000001"),
                "jan",
                "kowalksi",
                "test",
                "testPass"
        );

        bookRepositoryPort.addBook(testBook);
        userRepositoryPort.addUSer(testUser);

        loanService.borrowBook(
                UUID.fromString("0000-00-00-00-000000"),
                UUID.fromString("0000-00-00-00-000001")
        );

        Exception exception = assertThrows(PermissionException.class, () -> {
            loanService.returnBook(
                    UUID.fromString("0000-00-00-00-000000"),
                    UUID.fromString("0000-00-00-00-000002")
            );
        });

        assertEquals("this user does not own the book or does not exist.", exception.getMessage());
    }
}