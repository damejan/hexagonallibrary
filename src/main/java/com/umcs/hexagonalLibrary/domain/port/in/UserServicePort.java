package com.umcs.hexagonalLibrary.domain.port.in;

import com.umcs.hexagonalLibrary.domain.model.User;

import java.util.List;
import java.util.UUID;

public interface UserServicePort {

    List<User> getUsers();
    User addUser(User user);
    User getUserById(UUID id);
    User updateUser(UUID id, User user);
    User deleteUserById(UUID id);
}
