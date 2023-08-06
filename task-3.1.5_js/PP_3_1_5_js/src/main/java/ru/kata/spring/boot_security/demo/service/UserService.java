package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    UserDTO saveUser(UserDTO userDTO);

    List<UserDTO> findAllUsers();

    User findByEmail(String email);

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);

    UserDTO getById(Long id);
}