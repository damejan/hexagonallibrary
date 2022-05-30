package com.umcs.hexagonalLibrary.infrastructure.application.rest;

import com.umcs.hexagonalLibrary.domain.model.Loan;
import com.umcs.hexagonalLibrary.domain.model.User;
import com.umcs.hexagonalLibrary.domain.port.in.UserServicePort;
import com.umcs.hexagonalLibrary.infrastructure.application.rest.dto.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserServicePort userServicePort;

    public UserController(UserServicePort userServicePort) {
        this.userServicePort = userServicePort;
    }

    @GetMapping("/")
    public List<User> getUsers() {
        return userServicePort.getUsers();
    }

    @GetMapping("/{id}")
    public User getUSerById(@PathVariable UUID id) {
        return userServicePort.getUserById(id);
    }

    @PostMapping("/")
    public User addUSer(@RequestBody UserDto userDto) {
        return userServicePort.addUser(new User(null, userDto.getFirstName(), userDto.getLastName() , userDto.getLogin(), userDto.getPassword()));
    }

    @PatchMapping("/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody UserDto userDto) {
        return userServicePort.updateUser(id, new User(id, userDto.getFirstName(), userDto.getLastName(), userDto.getLogin(), userDto.getPassword()));
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable UUID id) {
        return userServicePort.deleteUserById(id);
    }
}
