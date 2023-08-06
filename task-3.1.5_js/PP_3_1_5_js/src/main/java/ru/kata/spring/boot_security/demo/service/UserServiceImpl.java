package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.exeption.UserNotFoundException;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepo;
import ru.kata.spring.boot_security.demo.repositories.UserRepo;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return userRepo.findAll().stream().map(User::toUserDTO).sorted(Comparator.comparing(UserDTO::getId)).collect(Collectors.toList());
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Transactional
    public UserDTO saveUser(UserDTO userDTO) {
        User user = User.builder()
                .firstname(userDTO.getFirstname())
                .lastname(userDTO.getLastname())
                .age(userDTO.getAge())
                .email(userDTO.getEmail())
                .roles(userDTO.getRoles().stream().map(x -> roleRepo.findByRole(x.getRole())).collect(Collectors.toSet()))
                .build();
        userRepo.save(user);
        return userDTO;
    }

    @Transactional
    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepo
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователя: " + userDTO.getEmail() + " не найдено"));
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());
        user.setAge(userDTO.getAge());
        user.setRoles(userDTO.getRoles().stream().map(x -> roleRepo.findByRole(x.getRole())).collect(Collectors.toSet()));
        userRepo.save(user);
        return userDTO;
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public UserDTO getById(Long id) {
        return UserDTO.toUserDTO(userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("User with id: " + id + " not found")));
    }
}