package ru.kata.spring.boot_security.demo.exeption;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserNotFoundException extends UsernameNotFoundException {
    public UserNotFoundException(String msg) {
        super(msg);
    }
}