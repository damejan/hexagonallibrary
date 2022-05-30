package com.umcs.hexagonalLibrary.domain.service;

import com.umcs.hexagonalLibrary.domain.model.Book;
import com.umcs.hexagonalLibrary.domain.model.Loan;
import com.umcs.hexagonalLibrary.domain.model.User;
import com.umcs.hexagonalLibrary.domain.port.out.UserRepositoryPort;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class UserService {

    private final UserRepositoryPort userRepositoryPort;

    public UserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public List<User> getUsers() {
        return this.userRepositoryPort.findAll();
    }

    public User addUser(User user) {
        return userRepositoryPort.addUSer(user);
    }

    public User getUserById(UUID id) {
        return this.userRepositoryPort.getUserById(id);
    }

    public User updateUser(UUID id, User user) {
        return this.userRepositoryPort.updateUser(id, user);
    }

    public User deleteUserById(UUID id) {
        return this.userRepositoryPort.deleteUserById(id);
    }
}
