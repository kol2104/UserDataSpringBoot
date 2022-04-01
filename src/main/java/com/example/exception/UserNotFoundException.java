package com.example.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String id) {
        super("User id not exist: " + id, new Throwable());
    }
}
