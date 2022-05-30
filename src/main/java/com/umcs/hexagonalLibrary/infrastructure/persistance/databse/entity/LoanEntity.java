package com.umcs.hexagonalLibrary.infrastructure.persistance.databse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoanEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    private BookEntity bookEntity;
    @OneToOne
    private UserEntity userEntity;
}
