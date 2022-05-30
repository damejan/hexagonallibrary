package com.umcs.hexagonalLibrary.infrastructure.application.rest;

import com.umcs.hexagonalLibrary.domain.model.Loan;
import com.umcs.hexagonalLibrary.domain.port.in.LoanServicePort;
import com.umcs.hexagonalLibrary.infrastructure.application.rest.dto.LoanContractDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan")
public class LoanController {
    private LoanServicePort loanServicePort;

    public LoanController(LoanServicePort loanServicePort) {
        this.loanServicePort = loanServicePort;
    }

    @GetMapping
    public List<Loan> getLoans() {
        return loanServicePort.getLoans();
    }

    @PostMapping("/borrow")
    public Loan borrowBook(@RequestBody LoanContractDto loanContractDto) {
        return loanServicePort.borrowBook(loanContractDto.getBookId(), loanContractDto.getUserId());
    }

    @PostMapping("/return")
    public Loan returnBook(@RequestBody LoanContractDto loanContractDto) {
        return loanServicePort.returnBook(loanContractDto.getBookId(), loanContractDto.getUserId());
    }
}
