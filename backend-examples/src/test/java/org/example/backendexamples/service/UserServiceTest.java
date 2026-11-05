package org.example.backendexamples.service;

import lombok.extern.slf4j.Slf4j;
import org.example.backendexamples.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void login() {
        var user = userService.login("1001", "1001");
        log.debug("{}", user);
    }

    @Test
    void addUser() {
        var user = User.builder()
                .account("1001")
                .name("BO")
                .role(User.USER)
                .build();
        userService.addUser(user);
    }
}