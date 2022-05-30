package com.umcs.hexagonalLibrary.domain.port.out;

import com.umcs.hexagonalLibrary.domain.model.Loan;

import java.util.List;
import java.util.UUID;

public interface LoanRepositoryPort {
    List<Loan> findAll();
    Loan addLoan(Loan loan);
    Loan getLoanById(UUID id);

    Loan getLoanByUserId(UUID id);
    Loan getLoanByBookId(UUID id);

    Loan deleteLoanById(UUID loanId);
}
