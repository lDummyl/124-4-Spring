package com.example.demo.services;


import com.example.demo.entity.Role;
import com.example.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Role saveRole(Role role);
    User saveUser(User user);
    void addRoleToUser(Long userId, String roleName);
    Optional<User> getUser(Long id);
    List<User> getUsers();
}
