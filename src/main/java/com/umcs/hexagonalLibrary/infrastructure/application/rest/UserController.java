package com.umcs.hexagonalLibrary.infrastructure.application.rest;

import com.umcs.hexagonalLibrary.domain.model.User;
import com.umcs.hexagonalLibrary.domain.port.in.UserServicePort;
import com.umcs.hexagonalLibrary.infrastructure.application.rest.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PostMapping("/")
    public User addUSer(UserDto userDto) {
        return userServicePort.addUser(new User(null, userDto.getFirstName(), userDto.getLastName() , userDto.getLogin(), userDto.getPassword()));
    }
}
