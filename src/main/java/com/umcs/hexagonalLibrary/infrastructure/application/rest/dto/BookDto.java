package com.umcs.hexagonalLibrary.infrastructure.application.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BookDto {
    private String author;
    private String title;
}
