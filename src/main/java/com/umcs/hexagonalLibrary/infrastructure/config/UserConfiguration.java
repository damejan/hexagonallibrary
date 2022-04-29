package com.umcs.hexagonalLibrary.infrastructure.config;

import com.umcs.hexagonalLibrary.domain.port.in.UserServicePort;
import com.umcs.hexagonalLibrary.domain.port.out.UserRepositoryPort;
import com.umcs.hexagonalLibrary.domain.service.UserService;
import com.umcs.hexagonalLibrary.infrastructure.application.adapter.UserServiceAdaptaer;
import com.umcs.hexagonalLibrary.infrastructure.persistance.inmemory.UserInMemoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

    @Bean
    public UserServicePort userServicePort(UserRepositoryPort userRepositoryPort) {
        return new UserServiceAdaptaer(new UserService(userRepositoryPort));
    }

    @Bean
    public UserRepositoryPort userRepositoryPort() {
        return new UserInMemoryAdapter();
    }

}
