package com.photoapp.api.users.PhotoappApiUsers.users.ui.controllers;

import com.photoapp.api.users.PhotoappApiUsers.users.ui.dto.UserDto;
import com.photoapp.api.users.PhotoappApiUsers.users.ui.model.request.CreateUserRequest;
import com.photoapp.api.users.PhotoappApiUsers.users.ui.model.response.CreateUserResponse;
import com.photoapp.api.users.PhotoappApiUsers.users.ui.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final Environment environment;
    private final UserService userService;

    public UsersController(Environment environment,
                           UserService userService) {
        this.environment = environment;
        this.userService = userService;
    }

    @GetMapping("/status/check")
    public String status() {
        return "working! port" + environment.getProperty("local.server.port");
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = mapper.map(createUserRequest, UserDto.class);
        UserDto createdUser = userService.createUser(userDto);

        CreateUserResponse createUserResponse = mapper.map(createdUser, CreateUserResponse.class);
        return new ResponseEntity<>(createUserResponse, HttpStatus.CREATED);
    }
}
