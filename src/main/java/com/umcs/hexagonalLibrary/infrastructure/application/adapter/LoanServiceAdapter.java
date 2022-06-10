package com.umcs.hexagonalLibrary.infrastructure.application.adapter;

import com.umcs.hexagonalLibrary.domain.model.Loan;
import com.umcs.hexagonalLibrary.domain.port.in.LoanServicePort;
import com.umcs.hexagonalLibrary.domain.service.LoanService;

import java.util.List;
import java.util.UUID;

public class LoanServiceAdapter implements LoanServicePort {

    private final LoanService loanService;

    public LoanServiceAdapter(LoanService loanService) {
        this.loanService = loanService;
    }

    @Override
    public List<Loan> getLoans() {
        return loanService.getLoans();
    }

    @Override
    public Loan borrowBook(UUID bookId, UUID userId) {
        return loanService.borrowBook(bookId, userId);
    }

    @Override
    public Loan returnBook(UUID bookId, UUID userId) {
        return loanService.returnBook(bookId, userId);
    }
}
