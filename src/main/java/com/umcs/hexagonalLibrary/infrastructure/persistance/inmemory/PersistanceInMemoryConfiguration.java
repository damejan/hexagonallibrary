package com.umcs.hexagonalLibrary.infrastructure.persistance.inmemory;

import com.umcs.hexagonalLibrary.domain.port.out.BookRepositoryPort;
import com.umcs.hexagonalLibrary.domain.port.out.BorrowRepositoryPort;
import com.umcs.hexagonalLibrary.domain.port.out.UserRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!prod")
public class PersistanceInMemoryConfiguration {
    @Bean
    public BookRepositoryPort bookRepositoryPort() {
        return new BookInMemoryAdapter();
    }

    @Bean
    public BorrowRepositoryPort borrowRepositoryPort() {
        return new BorrowInMemoryAdapter();
    }

    @Bean
    public UserRepositoryPort userRepositoryPort() {
        return new UserInMemoryAdapter();
    }
}
