package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepo;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepo repo;

    public RoleServiceImpl(RoleRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<Role> findAllRoles() {
        return repo.findAll();
    }

    public Role getRoleByName(String roleName) {
        return repo.findByRole(roleName);
    }

    @Override
    public Role getRoleById(Long id) {
        return repo.findRoleById(id);
    }

}