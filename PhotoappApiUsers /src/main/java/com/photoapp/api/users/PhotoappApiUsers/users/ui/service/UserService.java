package com.photoapp.api.users.PhotoappApiUsers.users.ui.service;

import com.photoapp.api.users.PhotoappApiUsers.users.ui.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDetails);
    UserDto getUserDetailsByEmail(String email);

}
