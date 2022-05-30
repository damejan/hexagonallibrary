package com.umcs.hexagonalLibrary.infrastructure.persistance.databse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String firstName;
    private String lastName;
    private String login;
    private String password;

}
