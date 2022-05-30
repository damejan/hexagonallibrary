package com.umcs.hexagonalLibrary.infrastructure.persistance.databse;

import com.umcs.hexagonalLibrary.domain.model.User;
import com.umcs.hexagonalLibrary.domain.port.out.UserRepositoryPort;
import com.umcs.hexagonalLibrary.infrastructure.persistance.databse.entity.UserEntity;
import com.umcs.hexagonalLibrary.infrastructure.persistance.databse.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserInDatabaseAdapter implements UserRepositoryPort {

    private final UserRepository userRepository;

    public UserInDatabaseAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll().stream()
                .map(userEntity -> new User(
                        userEntity.getId(),
                        userEntity.getFirstName(),
                        userEntity.getLastName(),
                        userEntity.getLogin(),
                        userEntity.getPassword()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public User addUSer(User user) {
        UserEntity result = userRepository.save(new UserEntity(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getLogin(),
                user.getPassword()
        ));
        return new User(result.getId(), result.getFirstName(), user.getLastName(), user.getLogin(), user.getPassword());
    }

    @Override
    public User getUserById(UUID id) {
        Optional<UserEntity> result = userRepository.findById(id);

        if(result.isEmpty()) {
            return null;
        }

        return new User(result.get().getId(), result.get().getFirstName(), result.get().getLastName(), result.get().getLogin(), result.get().getPassword());
    }

    @Override
    public User updateUser(UUID id, User user) {
        UserEntity userTuUpdate = userRepository.findById(id).get();

        userTuUpdate.setFirstName(user.getFirstName());
        userTuUpdate.setLastName(user.getLastName());
        userTuUpdate.setLogin(user.getLogin());
        userTuUpdate.setPassword(user.getPassword());

        UserEntity result = userRepository.save(userTuUpdate);

        return new User(result.getId(), result.getFirstName(), result.getLastName(), result.getLogin(), result.getPassword());
    }

    @Override
    public User deleteUserById(UUID id) {
        UserEntity userToDelete = userRepository.findById(id).get();
        userRepository.deleteById(id);
        return new User(userToDelete.getId(), userToDelete.getFirstName(), userToDelete.getLastName(), userToDelete.getLogin(), userToDelete.getPassword());
    }
}
