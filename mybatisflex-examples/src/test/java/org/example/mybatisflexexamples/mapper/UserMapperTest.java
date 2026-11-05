package org.example.mybatisflexexamples.mapper;

import lombok.extern.slf4j.Slf4j;
import org.example.mybatisflexexamples.entity.User;
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
    public void insert() {
        var user = User.builder()
                .name("BO")
                .build();
        userMapper.insert(user);
    }

    @Test
    public void selectOne() {
        var user = userMapper.selectOneById(45837036048448L);
        log.debug("{}", user);
    }
}