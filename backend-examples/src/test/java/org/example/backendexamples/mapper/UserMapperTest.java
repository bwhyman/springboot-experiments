package org.example.backendexamples.mapper;

import lombok.extern.slf4j.Slf4j;
import org.example.backendexamples.dto.UserAddress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void findUserAddressesByUserId() {
        UserAddress u = userMapper.findUserAddressesByUserId(54605495961664L);
        log.debug("{}", u);
    }
}