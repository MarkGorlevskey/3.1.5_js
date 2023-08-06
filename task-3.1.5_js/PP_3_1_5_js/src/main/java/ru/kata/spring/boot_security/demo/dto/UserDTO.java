package ru.kata.spring.boot_security.demo.dto;
import lombok.AllArgsConstructor; import lombok.Builder; import lombok.Data; import lombok.NoArgsConstructor; import ru.kata.spring.boot_security.demo.models.User; import ru.kata.spring.boot_security.demo.models.Role;
import java.util.HashSet; import java.util.Set; import java.util.stream.Collectors;
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private Integer age;
    private Set<RoleDTO> roles = new HashSet<>();
    public static UserDTO toUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .age(user.getAge())
                .roles(user.getRoles().stream().map(Role::toRoleDto).collect(Collectors.toSet()))
                .build();
    }
}