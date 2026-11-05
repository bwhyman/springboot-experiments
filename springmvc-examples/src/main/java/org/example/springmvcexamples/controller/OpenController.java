package org.example.springmvcexamples.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.springmvcexamples.component.JWTComponent;
import org.example.springmvcexamples.entity.User;
import org.example.springmvcexamples.exception.Code;
import org.example.springmvcexamples.service.UserService;
import org.example.springmvcexamples.vo.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/open/")
@RequiredArgsConstructor
public class OpenController {
    private final UserService userService;
    private final JWTComponent jwtComponent;

    @PostMapping("login")
    public ResultVO login(@RequestBody User rawUser, HttpServletResponse response) {
        var user = userService.login(rawUser.getAccount(), rawUser.getPassword());
        if(user == null) {
            return ResultVO.error(Code.LOGIN_ERROR);
        }
        var token = jwtComponent.encode(Map.of("uid", user.getId(), "role", user.getRole()));
        response.setHeader("token", token);
        return ResultVO.success(user);
    }
}
