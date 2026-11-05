package org.example.backendexamples.service;

import lombok.RequiredArgsConstructor;
import org.example.backendexamples.entity.User;
import org.example.backendexamples.mapper.UserMapper;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InitService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        var count = userMapper.getCount();
        if(count != 0) {
            return;
        }
        var account = "admin";
        var user = User.builder()
                .account(account)
                .password(passwordEncoder.encode(account))
                .name(account)
                .role(User.ADMIN)
                .build();
        userMapper.insert(user);
    }
}
