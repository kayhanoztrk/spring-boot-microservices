package com.photoapp.api.users.PhotoappApiUsers.users.ui.repository;

import com.photoapp.api.users.PhotoappApiUsers.users.ui.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
