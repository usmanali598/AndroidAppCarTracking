package com.example.usman.proretro.models;

/**
 * Created by usman on 09/03/2018.
 */

public class User {

    private String username;
    private String password;
    private String name;
    private String email;

    public User(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
