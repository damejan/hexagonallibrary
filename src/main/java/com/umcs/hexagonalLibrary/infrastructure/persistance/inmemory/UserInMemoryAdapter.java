package com.umcs.hexagonalLibrary.infrastructure.persistance.inmemory;

import com.umcs.hexagonalLibrary.domain.model.User;
import com.umcs.hexagonalLibrary.domain.port.out.UserRepositoryPort;

import java.util.*;

public class UserInMemoryAdapter implements UserRepositoryPort {

    private final Map<UUID, User> store = new HashMap<>();

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public User save(User user) {
        if(Objects.isNull(user.getId())) {
            user.setId(UUID.randomUUID());
        }
        return store.put(user.getId(), user);
    }

    @Override
    public User addUSer(User user) {
        return null;
    }

    @Override
    public User getUserById(UUID id) {
        return null;
    }

    @Override
    public User updateUser(UUID id, User user) {
        return null;
    }

    @Override
    public User deleteUserById(UUID id) {
        return null;
    }
}
