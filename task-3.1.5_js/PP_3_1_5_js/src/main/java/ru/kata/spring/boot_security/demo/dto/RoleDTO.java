package ru.kata.spring.boot_security.demo.dto;

import lombok.*;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Component
public class RoleDTO {
    private String role;

    public static RoleDTO toRoleDto(Role role) {
        return RoleDTO.builder()
                .role(role.getRole())
                .build();
    }
}