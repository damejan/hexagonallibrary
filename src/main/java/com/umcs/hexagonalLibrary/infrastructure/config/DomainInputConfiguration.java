package com.umcs.hexagonalLibrary.infrastructure.config;

import com.umcs.hexagonalLibrary.domain.port.in.BookServicePort;
import com.umcs.hexagonalLibrary.domain.port.in.BorrowServicePort;
import com.umcs.hexagonalLibrary.domain.port.in.UserServicePort;
import com.umcs.hexagonalLibrary.domain.port.out.BookRepositoryPort;
import com.umcs.hexagonalLibrary.domain.port.out.BorrowRepositoryPort;
import com.umcs.hexagonalLibrary.domain.port.out.UserRepositoryPort;
import com.umcs.hexagonalLibrary.domain.service.BookService;
import com.umcs.hexagonalLibrary.domain.service.BorrowService;
import com.umcs.hexagonalLibrary.domain.service.UserService;
import com.umcs.hexagonalLibrary.infrastructure.application.adapter.BookServiceAdapter;
import com.umcs.hexagonalLibrary.infrastructure.application.adapter.BorrowServiceAdapter;
import com.umcs.hexagonalLibrary.infrastructure.application.adapter.UserServiceAdaptaer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainInputConfiguration {

    @Bean
    public UserServicePort userServicePort(UserService userService) {
        return new UserServiceAdaptaer(userService);
    }

    @Bean
    public UserService userService(UserRepositoryPort userRepositoryPort) {
        return new UserService(userRepositoryPort);
    }

    @Bean
    public BorrowServicePort borrowServicePort(BorrowService borrowService) {
        return new BorrowServiceAdapter(borrowService);
    }

    @Bean
    public BorrowService borrowService(BorrowRepositoryPort borrowRepositoryPort, BookService bookService, UserService userService) {
        return new BorrowService(borrowRepositoryPort, bookService, userService);
    }

    @Bean
    public BookServicePort bookServicePort(BookService bookService) {
        return new BookServiceAdapter(bookService);
    }

    @Bean
    public BookService bookService(BookRepositoryPort bookRepositoryPort) {
        return new BookService(bookRepositoryPort);
    }
}
