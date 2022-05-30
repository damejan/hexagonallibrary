package com.umcs.hexagonalLibrary.infrastructure.application.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class LoanContractDto {
    private UUID bookId;
    private UUID userId;
}
