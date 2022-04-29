package com.umcs.hexagonalLibrary.infrastructure.application.adapter;

import com.umcs.hexagonalLibrary.domain.model.User;
import com.umcs.hexagonalLibrary.domain.port.in.UserServicePort;
import com.umcs.hexagonalLibrary.domain.service.UserService;

import java.util.List;
import java.util.UUID;

public class UserServiceAdaptaer implements UserServicePort {

    private final UserService userService;

    public UserServiceAdaptaer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @Override
    public User addUser(User user) {
        return userService.addUser(user);
    }

    @Override
    public User getUserById(UUID id) {
        return userService.getUserById(id);
    }

    @Override
    public User updateUser(UUID id, User user) {
        return userService.updateUser(id, user);
    }

    @Override
    public User deleteUserById(UUID id) {
        return userService.deleteUserById(id);
    }
}
