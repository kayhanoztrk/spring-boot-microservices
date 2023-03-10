package com.photoapp.api.users.PhotoappApiUsers.users.ui.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
public class CreateUserRequest {
    @NotNull(message = "FirstName can not be null!!")
    @Size(min = 2, message = "FirstName must not be less than 2 characters!")
    private String firstName;
    @NotNull(message = "LastName can not be null!!")
    @Size(min = 2, message = "LastName must not be less than 2 characters!")
    private String lastName;
    @NotNull(message = "Password can not be null!!")
    @Size(min = 8, max=15, message = "Password length should be in 8 and 15 characters")
    private String password;
    @NotNull(message = "Email can not be null!")
    @Email
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
