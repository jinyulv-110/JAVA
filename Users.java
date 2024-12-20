package com.jin.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String score;
    private Long last_login_time;
}