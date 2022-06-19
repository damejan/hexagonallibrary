package com.umcs.hexagonalLibrary.infrastructure.application.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class BorrowContractDto {
    private UUID bookId;
    private UUID userId;
}
