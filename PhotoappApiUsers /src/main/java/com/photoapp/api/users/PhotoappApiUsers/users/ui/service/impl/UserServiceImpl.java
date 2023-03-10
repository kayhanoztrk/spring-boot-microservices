package com.photoapp.api.users.PhotoappApiUsers.users.ui.service.impl;

import com.photoapp.api.users.PhotoappApiUsers.users.ui.dto.UserDto;
import com.photoapp.api.users.PhotoappApiUsers.users.ui.entity.User;
import com.photoapp.api.users.PhotoappApiUsers.users.ui.repository.UserRepository;
import com.photoapp.api.users.PhotoappApiUsers.users.ui.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDetails) {
        userDetails.setUserId(UUID.randomUUID().toString());
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User user = mapper.map(userDetails, User.class);
        user.setEncryptedPassword("TEST");
        userRepository.save(user);
        UserDto returnValue = mapper.map(user, UserDto.class);
        return returnValue;
    }
}
