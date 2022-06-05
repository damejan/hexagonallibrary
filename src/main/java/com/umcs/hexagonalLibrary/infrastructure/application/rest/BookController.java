package com.umcs.hexagonalLibrary.infrastructure.application.rest;

import com.umcs.hexagonalLibrary.domain.exceptions.BookNotFoundException;
import com.umcs.hexagonalLibrary.domain.exceptions.LibraryDomainException;
import com.umcs.hexagonalLibrary.domain.exceptions.UserNotFoundException;
import com.umcs.hexagonalLibrary.domain.model.Book;
import com.umcs.hexagonalLibrary.domain.model.User;
import com.umcs.hexagonalLibrary.domain.port.in.BookServicePort;
import com.umcs.hexagonalLibrary.infrastructure.application.rest.dto.BookDto;
import com.umcs.hexagonalLibrary.infrastructure.application.rest.dto.ErrorResponseDto;
import com.umcs.hexagonalLibrary.infrastructure.application.rest.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookServicePort bookServicePort;

    public BookController(BookServicePort bookServicePort) {
        this.bookServicePort = bookServicePort;
    }

    @GetMapping("/")
    public List<Book> getBooks() {
        return bookServicePort.getBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable UUID id) {
        return bookServicePort.getBookById(id);
    }

    @PostMapping("/")
    public Book addBook(@RequestBody BookDto bookDto) {
        return bookServicePort.addBook(new Book(null, bookDto.getAuthor(), bookDto.getTitle()));
    }

    @PatchMapping("/{id}")
    public Book updateBook(@PathVariable UUID id, @RequestBody BookDto bookDto) {
        return bookServicePort.updateBook(id, new Book(id, bookDto.getAuthor(), bookDto.getTitle()));
    }

    @DeleteMapping("/{id}")
    public Book deleteBook(@PathVariable UUID id) {
        return bookServicePort.deleteBookById(id);
    }

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<ErrorResponseDto> notFoundException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(ex.getLocalizedMessage()));
    }
}
