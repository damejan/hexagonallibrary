package com.umcs.hexagonalLibrary.infrastructure.persistance.databse;

import com.umcs.hexagonalLibrary.domain.model.Book;
import com.umcs.hexagonalLibrary.domain.model.Loan;
import com.umcs.hexagonalLibrary.domain.model.User;
import com.umcs.hexagonalLibrary.domain.port.out.LoanRepositoryPort;
import com.umcs.hexagonalLibrary.infrastructure.persistance.databse.entity.BookEntity;
import com.umcs.hexagonalLibrary.infrastructure.persistance.databse.entity.LoanEntity;
import com.umcs.hexagonalLibrary.infrastructure.persistance.databse.entity.UserEntity;
import com.umcs.hexagonalLibrary.infrastructure.persistance.databse.repository.LoanRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class LoanInDatabaseAdapter implements LoanRepositoryPort {

    private final LoanRepository loanRepository;

    public LoanInDatabaseAdapter(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public List<Loan> findAll() {
        return loanRepository.findAll().stream()
                .map(loanEntity -> new Loan(
                        loanEntity.getId(),
                        new Book(
                                loanEntity.getBookEntity().getId(),
                                loanEntity.getBookEntity().getAuthor(),
                                loanEntity.getBookEntity().getTitle()
                        ),
                        new User(
                                loanEntity.getUserEntity().getId(),
                                loanEntity.getUserEntity().getFirstName(),
                                loanEntity.getUserEntity().getLastName(),
                                loanEntity.getUserEntity().getLogin(),
                                loanEntity.getUserEntity().getPassword()
                        )
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Loan addLoan(Loan loan) {
        LoanEntity result = loanRepository.save(new LoanEntity(
                loan.getId(),
                new BookEntity(
                        loan.getBook().getId(),
                        loan.getBook().getAuthor(),
                        loan.getBook().getTitle()
                ),
                new UserEntity(
                        loan.getUser().getId(),
                        loan.getUser().getFirstName(),
                        loan.getUser().getLastName(),
                        loan.getUser().getLogin(),
                        loan.getUser().getPassword()
                )
        ));

        return new Loan(
                result.getId(),
                new Book(
                        result.getBookEntity().getId(),
                        result.getBookEntity().getAuthor(),
                        result.getBookEntity().getTitle()
                ),
                new User(
                        result.getUserEntity().getId(),
                        result.getUserEntity().getFirstName(),
                        result.getUserEntity().getLastName(),
                        result.getUserEntity().getLogin(),
                        result.getUserEntity().getPassword()
                )
        );
    }

    @Override
    public Loan getLoanById(UUID id) {
        Optional<LoanEntity> loanEntity = loanRepository.findById(id);

        if(loanEntity.isEmpty()) {
            return null;
        }

        return new Loan(
                loanEntity.get().getId(),
                new Book(
                        loanEntity.get().getBookEntity().getId(),
                        loanEntity.get().getBookEntity().getAuthor(),
                        loanEntity.get().getBookEntity().getTitle()
                ),
                new User(
                        loanEntity.get().getUserEntity().getId(),
                        loanEntity.get().getUserEntity().getFirstName(),
                        loanEntity.get().getUserEntity().getLastName(),
                        loanEntity.get().getUserEntity().getLogin(),
                        loanEntity.get().getUserEntity().getPassword()
                )
        );
    }

    @Override
    public Loan getLoanByUserId(UUID id) {
        Optional<LoanEntity> result = loanRepository.findByUserEntity_id(id);

        if(result.isEmpty()) {
            return null;
        }

        return new Loan(
                result.get().getId(),
                new Book(
                        result.get().getBookEntity().getId(),
                        result.get().getBookEntity().getAuthor(),
                        result.get().getBookEntity().getTitle()
                ),
                new User(
                        result.get().getUserEntity().getId(),
                        result.get().getUserEntity().getFirstName(),
                        result.get().getUserEntity().getLastName(),
                        result.get().getUserEntity().getLogin(),
                        result.get().getUserEntity().getPassword()
                )
        );
    }

    @Override
    public Loan getLoanByBookId(UUID id) {
        Optional<LoanEntity> result = loanRepository.findByBookEntity_id(id);

        if(result.isEmpty()) {
            return null;
        }
        return new Loan(
                result.get().getId(),
                new Book(
                        result.get().getBookEntity().getId(),
                        result.get().getBookEntity().getAuthor(),
                        result.get().getBookEntity().getTitle()
                ),
                new User(
                        result.get().getUserEntity().getId(),
                        result.get().getUserEntity().getFirstName(),
                        result.get().getUserEntity().getLastName(),
                        result.get().getUserEntity().getLogin(),
                        result.get().getUserEntity().getPassword()
                )
        );
    }

    @Override
    public Loan deleteLoanById(UUID loanId) {
        LoanEntity loanToDelete = loanRepository.findById(loanId).get();
        loanRepository.deleteById(loanId);
        return new Loan(
                loanToDelete.getId(),
                new Book(
                        loanToDelete.getBookEntity().getId(),
                        loanToDelete.getBookEntity().getAuthor(),
                        loanToDelete.getBookEntity().getTitle()
                ),
                new User(
                        loanToDelete.getUserEntity().getId(),
                        loanToDelete.getUserEntity().getFirstName(),
                        loanToDelete.getUserEntity().getLastName(),
                        loanToDelete.getUserEntity().getLogin(),
                        loanToDelete.getUserEntity().getPassword()
                )
        );
    }
}
