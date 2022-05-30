package com.umcs.hexagonalLibrary.infrastructure.persistance.inmemory;

import com.umcs.hexagonalLibrary.domain.model.Loan;
import com.umcs.hexagonalLibrary.domain.model.User;
import com.umcs.hexagonalLibrary.domain.port.out.BookRepositoryPort;
import com.umcs.hexagonalLibrary.domain.port.out.LoanRepositoryPort;
import com.umcs.hexagonalLibrary.domain.port.out.UserRepositoryPort;

import java.util.*;
import java.util.stream.Collectors;

public class LoanInMemoryAdapter implements LoanRepositoryPort {

    private final Map<UUID, Loan> store = new HashMap<>();

    private final BookRepositoryPort bookRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;

    public LoanInMemoryAdapter(BookRepositoryPort bookRepositoryPort, UserRepositoryPort userRepositoryPort) {
        this.bookRepositoryPort = bookRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public List<Loan> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Loan addLoan(Loan loan) {
        if(Objects.isNull(loan.getId())) {
            loan.setId(UUID.randomUUID());
        }
        store.put(loan.getId(), loan);

        return loan;
    }

    @Override
    public Loan getLoanById(UUID id) {
        return store.get(id);
    }

    @Override
    public Loan getLoanByUserId(UUID id) {
        var loans = store.values().stream()
                .filter(loan -> loan.getUser().getId().equals(id))
                .toList();

        if(loans.isEmpty()) {
            return null;
        }
        return loans.get(0);
    }

    @Override
    public Loan getLoanByBookId(UUID id) {
        var loans = store.values().stream()
                .filter(loan -> loan.getBook().getId().equals(id))
                .toList();

        if(loans.isEmpty()) {
            return null;
        }
        return loans.get(0);
    }

    @Override
    public Loan deleteLoanById(UUID loanId) {
        return store.remove(loanId);
    }
}
