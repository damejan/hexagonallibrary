package com.umcs.hexagonalLibrary.domain.port.out;

import com.umcs.hexagonalLibrary.domain.model.User;

import java.util.List;
import java.util.UUID;

public interface UserRepositoryPort {

    List<User> findAll();
    User save(User user);
    User addUSer(User user);
    User getUserById(UUID id);
    User updateUser(UUID id, User user);
    User deleteUserById(UUID id);
}
