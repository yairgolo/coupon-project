package com.yair.couponproject.entities;

import com.yair.couponproject.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    //------------ user model --------------
    private long id;
    private String email;
    private String password;
    private Role role;

    public User(String email, Role role) {
        this.email = email;
        this.role = role;
    }
}
