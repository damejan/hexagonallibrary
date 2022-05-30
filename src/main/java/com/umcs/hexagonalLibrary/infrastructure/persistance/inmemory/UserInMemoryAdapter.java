package com.umcs.hexagonalLibrary.infrastructure.persistance.inmemory;

import com.umcs.hexagonalLibrary.domain.model.User;
import com.umcs.hexagonalLibrary.domain.port.out.UserRepositoryPort;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class UserInMemoryAdapter implements UserRepositoryPort {

    private final Map<UUID, User> store = new HashMap<>();
    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public User addUSer(User user) {
        if(Objects.isNull(user.getId())) {
            user.setId(UUID.randomUUID());
        }
        store.put(user.getId(), user);
        return user;
    }

    @Override
    public User getUserById(UUID id) {
        return store.get(id);
    }

    @Override
    public User updateUser(UUID id, User user) {
        return store.replace(id, user);
    }

    @Override
    public User deleteUserById(UUID id) {
        return store.remove(id);
    }
}
