package com.umcs.hexagonalLibrary.infrastructure.application.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDto {
    String firstName;
    String lastName;
    String login;
    String password;
}
