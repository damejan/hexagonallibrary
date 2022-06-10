package com.umcs.hexagonalLibrary.domain.port.in;

import com.umcs.hexagonalLibrary.domain.model.Book;
import com.umcs.hexagonalLibrary.domain.model.Loan;

import java.util.List;
import java.util.UUID;

public interface LoanServicePort {
    List<Loan> getLoans();
    Loan borrowBook(UUID bookId, UUID userId);
    Loan returnBook(UUID bookId, UUID userId);
}
