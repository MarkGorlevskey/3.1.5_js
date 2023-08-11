package ru.kata.spring.boot_security.demo.controller;
import org.springframework.http.HttpStatus; import org.springframework.http.ResponseEntity; import org.springframework.web.bind.annotation.*; import ru.kata.spring.boot_security.demo.dto.UserDTO; import ru.kata.spring.boot_security.demo.models.User; import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
@RestController @RequestMapping("/api")
public class AdminController {
    private final UserService userService;
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAllUsers());
    }

    @PostMapping("/new")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userDTO));
    }

    @PatchMapping("/user/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUser(id, userDTO));
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("user/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getById(id));
    }

    @GetMapping("/user")
    public ResponseEntity<User> getCurrentUser(Principal principal) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByEmail(principal.getName()));
    }
}