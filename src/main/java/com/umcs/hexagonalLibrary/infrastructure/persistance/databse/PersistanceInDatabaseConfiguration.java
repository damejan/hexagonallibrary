package com.umcs.hexagonalLibrary.infrastructure.persistance.databse;

import com.umcs.hexagonalLibrary.domain.port.out.BookRepositoryPort;
import com.umcs.hexagonalLibrary.domain.port.out.LoanRepositoryPort;
import com.umcs.hexagonalLibrary.domain.port.out.UserRepositoryPort;
import com.umcs.hexagonalLibrary.infrastructure.persistance.databse.repository.BookRepository;
import com.umcs.hexagonalLibrary.infrastructure.persistance.databse.repository.LoanRepository;
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
    public LoanRepositoryPort loanRepositoryPort(LoanRepository loanRepository) {
        return new LoanInDatabaseAdapter(loanRepository);
    }

    @Bean
    public UserRepositoryPort userRepositoryPort(UserRepository userRepository) {
        return new UserInDatabaseAdapter(userRepository);
    }
}
