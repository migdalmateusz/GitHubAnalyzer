package com.migdalmateusz.githubproject.domain;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String user) {
        super("Could not find user: " + user);
    }
}
