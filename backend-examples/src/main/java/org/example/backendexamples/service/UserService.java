package org.example.backendexamples.service;

import lombok.RequiredArgsConstructor;
import org.example.backendexamples.dto.UserAddress;
import org.example.backendexamples.entity.Address;
import org.example.backendexamples.entity.User;
import org.example.backendexamples.mapper.AddressMapper;
import org.example.backendexamples.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final AddressMapper addressMapper;
    private final PasswordEncoder passwordEncoder;

    public User login(String account, String password) {
        var user = userMapper.findByAccount(account);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            return null;
        }
        return user;
    }

    @Transactional
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getAccount()));
        userMapper.insert(user);
    }
    @Transactional
    public void addAddress(Address address) {
        addressMapper.insert(address);
    }

    public UserAddress listUserAddresses(long uid) {
        return userMapper.findUserAddressesByUserId(uid);
    }

    public List<User> listUsers(int pageNumber, int pageSize) {
        return userMapper.findByPage(pageNumber, pageSize);
    }
}
