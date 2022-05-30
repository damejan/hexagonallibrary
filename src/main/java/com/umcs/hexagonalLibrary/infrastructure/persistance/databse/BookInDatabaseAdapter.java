package com.umcs.hexagonalLibrary.infrastructure.persistance.databse;

import com.umcs.hexagonalLibrary.domain.model.Book;
import com.umcs.hexagonalLibrary.domain.port.out.BookRepositoryPort;
import com.umcs.hexagonalLibrary.infrastructure.persistance.databse.entity.BookEntity;
import com.umcs.hexagonalLibrary.infrastructure.persistance.databse.repository.BookRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookInDatabaseAdapter implements BookRepositoryPort {

    private final BookRepository bookRepository;

    public BookInDatabaseAdapter(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll().stream()
                .map(bookEntity -> new Book(
                        bookEntity.getId(),
                        bookEntity.getAuthor(),
                        bookEntity.getTitle()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Book addBook(Book book) {
        BookEntity result = bookRepository.save(new BookEntity(
                book.getId(),
                book.getAuthor(),
                book.getTitle()
        ));
        return new Book(result.getId(), result.getAuthor(), result.getTitle());
    }

    @Override
    public Book getBookById(UUID id) {
        Optional<BookEntity> result = bookRepository.findById(id);

        if(result.isEmpty()) {
            return null;
        }

        return new Book(result.get().getId(), result.get().getAuthor(), result.get().getTitle());
    }

    @Override
    public Book updateBook(UUID id, Book book) {
        BookEntity bookToUpdate = bookRepository.findById(id).get();

        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setTitle(book.getTitle());

        BookEntity result = bookRepository.save(bookToUpdate);

        return new Book(result.getId(), result.getAuthor(), result.getTitle());
    }

    @Override
    public Book deleteBookById(UUID id) {
        BookEntity bookToDelete = bookRepository.findById(id).get();
        bookRepository.deleteById(id);
        return new Book(bookToDelete.getId(), bookToDelete.getAuthor(), bookToDelete.getTitle());
    }
}
