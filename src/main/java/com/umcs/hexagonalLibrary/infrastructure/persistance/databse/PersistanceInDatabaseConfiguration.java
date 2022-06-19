package com.umcs.hexagonalLibrary.infrastructure.persistance.databse;

import com.umcs.hexagonalLibrary.domain.port.out.BookRepositoryPort;
import com.umcs.hexagonalLibrary.domain.port.out.BorrowRepositoryPort;
import com.umcs.hexagonalLibrary.domain.port.out.UserRepositoryPort;
import com.umcs.hexagonalLibrary.infrastructure.persistance.databse.repository.BookRepository;
import com.umcs.hexagonalLibrary.infrastructure.persistance.databse.repository.BorrowRepository;
import com.umcs.hexagonalLibrary.infrastructure.persistance.databse.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class PersistanceInDatabaseConfiguration {
    @Bean
    public BookRepositoryPort bookRepositoryPort(BookRepository bookRepository) {
        return new BookInDatabaseAdapter(bookRepository);
    }

    @Bean
    public BorrowRepositoryPort borrowRepositoryPort(BorrowRepository borrowRepository) {
        return new BorrowInDatabaseAdapter(borrowRepository);
    }

    @Bean
    public UserRepositoryPort userRepositoryPort(UserRepository userRepository) {
        return new UserInDatabaseAdapter(userRepository);
    }
}
