package ru.kata.spring.boot_security.demo.models;


import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import ru.kata.spring.boot_security.demo.dto.RoleDTO;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role")
    private String role;

    @Override
    public String getAuthority() {
        return role;
    }

    @Override
    public String toString() {
        return role.substring(5);
    }

    @Bean
    public Role roleWithOutPrefix(Role role) {
        return Role.builder()
                .role(role.getRole().substring(5))
                .build();
    }
    public RoleDTO toRoleDto() {
        return RoleDTO.toRoleDto(this);
    }

}