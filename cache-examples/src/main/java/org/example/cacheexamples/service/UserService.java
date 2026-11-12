package org.example.cacheexamples.service;

import org.example.cacheexamples.entity.User;
import org.example.cacheexamples.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Cacheable(value = "users", key = "#uid")
    public User getUser(String uid) {
        User user = userRepository.findById(uid);
        log.debug("called UserService getUser() user: {}", user);
        return user;
    }

    @Cacheable(value = "users")
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    @CachePut(value = "users", key = "#user.id")
    public User updateUser(User user) {
        User u = userRepository.update(user);
        log.debug("updateUser(), user: {}", u);
        return u;
    }

    @CacheEvict(value = "users", allEntries = true)
      public void removeUser(String uid) {
        // 从缓存删除，没有调用模拟的持久层删除
    }
}
