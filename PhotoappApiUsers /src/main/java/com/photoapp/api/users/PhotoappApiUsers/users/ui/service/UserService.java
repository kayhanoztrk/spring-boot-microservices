package com.photoapp.api.users.PhotoappApiUsers.users.ui.service;

import com.photoapp.api.users.PhotoappApiUsers.users.ui.dto.UserDto;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
public interface UserService {
    UserDto createUser(UserDto userDetails);
}
