package org.example.springmvcexamples.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.springmvcexamples.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;

    public User login(String account, String rawPassword) {
        return USER.stream()
                .filter(u -> u.getAccount().equals(account))
                .filter(u -> passwordEncoder.matches(rawPassword, u.getPassword()))
                .findFirst()
                .orElse(null);
    }

    public List<User> listUsers() {
        return USER;
    }

    public User getUserByAccount(String account) {
        return USER.stream()
                .filter(u -> u.getAccount().equals(account))
                .findFirst()
                .orElse(null);
    }

    private List<User> USER;
    @PostConstruct
    private void create() {
        var u1 = User.builder()
                .id(1L)
                .name("admin")
                .account("admin")
                .password(passwordEncoder.encode("admin"))
                .role(User.ADMIN)
                .createTime(LocalDateTime.now())
                .build();
        var u2 = User.builder()
                .id(4L)
                .name("BO")
                .account("1001")
                .password(passwordEncoder.encode("1001"))
                .role(User.USER)
                .createTime(LocalDateTime.now())
                .build();
        USER = List.of(u1, u2);

    }

}
