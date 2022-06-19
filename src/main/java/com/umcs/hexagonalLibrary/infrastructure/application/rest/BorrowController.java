package com.umcs.hexagonalLibrary.infrastructure.application.rest;

import com.umcs.hexagonalLibrary.domain.exceptions.*;
import com.umcs.hexagonalLibrary.domain.model.BorrowEntry;
import com.umcs.hexagonalLibrary.domain.port.in.BorrowServicePort;
import com.umcs.hexagonalLibrary.infrastructure.application.rest.dto.ErrorResponseDto;
import com.umcs.hexagonalLibrary.infrastructure.application.rest.dto.BorrowContractDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowEntries")
public class BorrowController {
    private BorrowServicePort borrowServicePort;

    public BorrowController(BorrowServicePort borrowServicePort) {
        this.borrowServicePort = borrowServicePort;
    }

    @GetMapping
    public List<BorrowEntry> getBorrowEntries() {
        return borrowServicePort.getBorrowEntries();
    }

    @PostMapping("/borrow")
    public BorrowEntry borrowBook(@RequestBody BorrowContractDto borrowContractDto) {
        return borrowServicePort.borrowBook(borrowContractDto.getBookId(), borrowContractDto.getUserId());
    }

    @PostMapping("/return")
    public BorrowEntry returnBook(@RequestBody BorrowContractDto borrowContractDto) {
        return borrowServicePort.returnBook(borrowContractDto.getBookId(), borrowContractDto.getUserId());
    }

    @ExceptionHandler({BookAlreadyBorrowedException.class, BookNotBorrowedException.class, PermissionException.class})
    public ResponseEntity<ErrorResponseDto> unProcessableEntityException(LibraryDomainException ex) {
        return ResponseEntity.unprocessableEntity().body(new ErrorResponseDto(ex.getLocalizedMessage()));
    }

    @ExceptionHandler({BookNotFoundException.class, UserNotFoundException.class})
    public ResponseEntity<ErrorResponseDto> notFoundException(LibraryDomainException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(ex.getLocalizedMessage()));
    }

}
