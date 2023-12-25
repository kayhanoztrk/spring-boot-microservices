package com.photoapp.api.users.PhotoappApiUsers.users.ui.service.impl;

import com.photoapp.api.users.PhotoappApiUsers.users.ui.dto.UserDto;
import com.photoapp.api.users.PhotoappApiUsers.users.ui.entity.User;
import com.photoapp.api.users.PhotoappApiUsers.users.ui.repository.UserRepository;
import com.photoapp.api.users.PhotoappApiUsers.users.ui.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    public UserDto createUser(UserDto userDetails) {
        userDetails.setUserId(UUID.randomUUID().toString());
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User user = mapper.map(userDetails, User.class);
        user.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
        userRepository.save(user);
        UserDto returnValue = mapper.map(user, UserDto.class);
        return returnValue;
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user != null) throw new UsernameNotFoundException(email + " not found!");

        return new ModelMapper().map(user, UserDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user == null) throw new UsernameNotFoundException(username + " not found!");

       return (UserDetails) new User(user.getEmail(), user.getEncryptedPassword(),true,true,true,true);
    }
}
