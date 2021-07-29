package com.example.demo.services.impl;


import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Role saveRole(Role role) {
        log.info("Save new role to the DB, name: {} ", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public User saveUser(User user) {
        log.info("Save new user to the DB, login: {} ", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void addRoleToUser(Long userId, String roleName) {
        log.info("Adding role to user, userId: {}, roleName: {}", userId, roleName);
        Optional<User> byId = userRepository.findById(userId);
        byId.ifPresent(user -> {
            Role role = roleRepository.findByName(roleName);
            user.getRoles().add(role);
            User save = userRepository.save(user);
            log.info("{}", save);
        });
    }

    @Override
    public Optional<User> getUser(Long id) {
        log.info("Getting user id: {}", id);
        return userRepository.findById(id);
    }

    @Override
    public List<User> getUsers() {
        log.info("Getting all users");
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> optional = Optional.ofNullable(userRepository.findByUsername(name)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User not found.");
                }));

        if(optional.isPresent()) {
            User user = optional.get();

            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(el -> authorities.add(new SimpleGrantedAuthority(el.getName())));

            return new org.springframework.security.core.userdetails
                    .User(user.getUsername(), user.getPassword(), authorities);
        }
        return null;
    }
}
