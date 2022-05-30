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
    public Loan getLoanById(UUID id) {
        return loanService.getLoanById(id);
    }

    @Override
    public Loan addLoan(Loan loan) {
        return loanService.addLoan(loan);
    }

    @Override
    public Loan getLoanByUserId(UUID userId) {
        return loanService.getLoanByUserId(userId);
    }

    @Override
    public Loan getLoanByBookId(UUID bookId) {
        return loanService.getLoanByBookId(bookId);
    }

    @Override
    public Loan deleteLoanById(UUID loanId) {
        return loanService.deleteLoanById(loanId);
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
