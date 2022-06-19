package com.umcs.hexagonalLibrary.infrastructure.persistance.databse;

import com.umcs.hexagonalLibrary.domain.model.Book;
import com.umcs.hexagonalLibrary.domain.model.BorrowEntry;
import com.umcs.hexagonalLibrary.domain.model.User;
import com.umcs.hexagonalLibrary.domain.port.out.BorrowRepositoryPort;
import com.umcs.hexagonalLibrary.infrastructure.persistance.databse.entity.BookEntity;
import com.umcs.hexagonalLibrary.infrastructure.persistance.databse.entity.BorrowEntryEntity;
import com.umcs.hexagonalLibrary.infrastructure.persistance.databse.entity.UserEntity;
import com.umcs.hexagonalLibrary.infrastructure.persistance.databse.repository.BorrowRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class BorrowInDatabaseAdapter implements BorrowRepositoryPort {

    private final BorrowRepository borrowRepository;

    public BorrowInDatabaseAdapter(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    @Override
    public List<BorrowEntry> findAll() {
        return borrowRepository.findAll().stream()
                .map(borrowEntryEntity -> new BorrowEntry(
                        borrowEntryEntity.getId(),
                        bookEntityToBook(borrowEntryEntity.getBookEntity()),
                        userEntityToUser(borrowEntryEntity.getUserEntity())
                ))
                .collect(Collectors.toList());
    }

    @Override
    public BorrowEntry addBorrowEntry(BorrowEntry borrowEntry) {
        BorrowEntryEntity result = borrowRepository.save(new BorrowEntryEntity(
                borrowEntry.getId(),
                bookToEntityBook(borrowEntry.getBook()),
                userToEntityUser(borrowEntry.getUser())
        ));

        return new BorrowEntry(
                result.getId(),
                bookEntityToBook(result.getBookEntity()),
                userEntityToUser(result.getUserEntity())
        );
    }

    @Override
    public BorrowEntry getBorrowEntryById(UUID id) {
        Optional<BorrowEntryEntity> result = borrowRepository.findById(id);

        if(result.isEmpty()) {
            return null;
        }

        return new BorrowEntry(
                result.get().getId(),
                bookEntityToBook(result.get().getBookEntity()),
                userEntityToUser(result.get().getUserEntity())
        );
    }

    @Override
    public BorrowEntry getBorrowEntryByUserId(UUID id) {
        Optional<BorrowEntryEntity> result = borrowRepository.findByUserEntity_id(id);

        if(result.isEmpty()) {
            return null;
        }

        return new BorrowEntry(
                result.get().getId(),
                bookEntityToBook(result.get().getBookEntity()),
                userEntityToUser(result.get().getUserEntity())
        );
    }

    @Override
    public BorrowEntry getBorrowEntryByBookId(UUID id) {
        Optional<BorrowEntryEntity> result = borrowRepository.findByBookEntity_id(id);

        if(result.isEmpty()) {
            return null;
        }
        return new BorrowEntry(
                result.get().getId(),
                bookEntityToBook(result.get().getBookEntity()),
                userEntityToUser(result.get().getUserEntity())
        );
    }

    @Override
    public BorrowEntry deleteBorrowEntryById(UUID borrowEntryId) {
        BorrowEntryEntity borrowEntryToDelete = borrowRepository.findById(borrowEntryId).get();
        borrowRepository.deleteById(borrowEntryId);
        return new BorrowEntry(
                borrowEntryToDelete.getId(),
                bookEntityToBook(borrowEntryToDelete.getBookEntity()),
                userEntityToUser(borrowEntryToDelete.getUserEntity())
        );
    }

    private Book bookEntityToBook(BookEntity bookEntity) {
        return new Book(
                bookEntity.getId(),
                bookEntity.getAuthor(),
                bookEntity.getTitle()
        );
    }

    private BookEntity bookToEntityBook(Book book) {
        return new BookEntity(
                book.getId(),
                book.getAuthor(),
                book.getTitle()
        );
    }

    private User userEntityToUser(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getLogin(),
                userEntity.getPassword()
        );
    }

    private UserEntity userToEntityUser(User user) {
        return new UserEntity(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getLogin(),
                user.getPassword()
        );
    }
}
