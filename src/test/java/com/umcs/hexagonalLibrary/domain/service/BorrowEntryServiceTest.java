package com.umcs.hexagonalLibrary.domain.service;

import com.umcs.hexagonalLibrary.domain.exceptions.*;
import com.umcs.hexagonalLibrary.domain.model.Book;
import com.umcs.hexagonalLibrary.domain.model.BorrowEntry;
import com.umcs.hexagonalLibrary.domain.model.User;
import com.umcs.hexagonalLibrary.domain.port.out.BookRepositoryPort;
import com.umcs.hexagonalLibrary.domain.port.out.BorrowRepositoryPort;
import com.umcs.hexagonalLibrary.domain.port.out.UserRepositoryPort;
import com.umcs.hexagonalLibrary.infrastructure.persistance.inmemory.BookInMemoryAdapter;
import com.umcs.hexagonalLibrary.infrastructure.persistance.inmemory.BorrowInMemoryAdapter;
import com.umcs.hexagonalLibrary.infrastructure.persistance.inmemory.UserInMemoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BorrowEntryServiceTest {

    private BookRepositoryPort bookRepositoryPort;
    private UserRepositoryPort userRepositoryPort;
    private BorrowRepositoryPort borrowRepositoryPort;
    private BorrowService borrowService;

    @BeforeEach
    void setup() {
        bookRepositoryPort = new BookInMemoryAdapter();
        userRepositoryPort = new UserInMemoryAdapter();
        borrowRepositoryPort = new BorrowInMemoryAdapter();

        BookService bookService = new BookService(bookRepositoryPort);
        UserService userService = new UserService(userRepositoryPort);

        borrowService = new BorrowService(borrowRepositoryPort, bookService, userService);
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

        borrowService.borrowBook(
                UUID.fromString("0000-00-00-00-000000"),
                UUID.fromString("0000-00-00-00-000001")
        );

        List<BorrowEntry> entries = borrowRepositoryPort.findAll();
        assertEquals(1, entries.size());
        assertEquals(testBook.getId(), entries.get(0).getBook().getId());
        assertEquals(testBook.getAuthor(), entries.get(0).getBook().getAuthor());
        assertEquals(testBook.getTitle(), entries.get(0).getBook().getTitle());
        assertEquals(testUser.getId(), entries.get(0).getUser().getId());
        assertEquals(testUser.getFirstName(), entries.get(0).getUser().getFirstName());
        assertEquals(testUser.getLastName(), entries.get(0).getUser().getLastName());
        assertEquals(testUser.getLogin(), entries.get(0).getUser().getLogin());
        assertEquals(testUser.getPassword(), entries.get(0).getUser().getPassword());
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
            borrowService.borrowBook(
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

        borrowService.borrowBook(
                UUID.fromString("0000-00-00-00-000000"),
                UUID.fromString("0000-00-00-00-000001")
        );

        Exception exception = assertThrows(BookAlreadyBorrowedException.class, () -> {
            borrowService.borrowBook(
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
            borrowService.borrowBook(
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

        borrowService.borrowBook(
                UUID.fromString("0000-00-00-00-000000"),
                UUID.fromString("0000-00-00-00-000001")
        );

        BorrowEntry returnedBorrowEntry = borrowService.returnBook(
                UUID.fromString("0000-00-00-00-000000"),
                UUID.fromString("0000-00-00-00-000001")
        );
        List<BorrowEntry> allEntries = borrowRepositoryPort.findAll();

        assertEquals(0, allEntries.size());
        assertEquals(testBook.getId(), returnedBorrowEntry.getBook().getId());
        assertEquals(testBook.getAuthor(), returnedBorrowEntry.getBook().getAuthor());
        assertEquals(testBook.getTitle(), returnedBorrowEntry.getBook().getTitle());
        assertEquals(testUser.getId(), returnedBorrowEntry.getUser().getId());
        assertEquals(testUser.getFirstName(), returnedBorrowEntry.getUser().getFirstName());
        assertEquals(testUser.getLastName(), returnedBorrowEntry.getUser().getLastName());
        assertEquals(testUser.getLogin(), returnedBorrowEntry.getUser().getLogin());
        assertEquals(testUser.getPassword(), returnedBorrowEntry.getUser().getPassword());
    }

    @Test
    void returnBookWhenBookNotBorrowedYetOrDoesNotExist() {
        Exception exception = assertThrows(BookNotBorrowedException.class, () -> {
            borrowService.returnBook(
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

        borrowService.borrowBook(
                UUID.fromString("0000-00-00-00-000000"),
                UUID.fromString("0000-00-00-00-000001")
        );

        Exception exception = assertThrows(PermissionException.class, () -> {
            borrowService.returnBook(
                    UUID.fromString("0000-00-00-00-000000"),
                    UUID.fromString("0000-00-00-00-000002")
            );
        });

        assertEquals("this user does not own the book or does not exist.", exception.getMessage());
    }
}