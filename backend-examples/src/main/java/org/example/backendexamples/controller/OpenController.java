package org.example.backendexamples.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.backendexamples.component.JWTComponent;
import org.example.backendexamples.entity.User;
import org.example.backendexamples.exception.Code;
import org.example.backendexamples.service.UserService;
import org.example.backendexamples.vo.RequestAttributeConst;
import org.example.backendexamples.vo.ResultVO;
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
        var token = jwtComponent.encode(Map.of(RequestAttributeConst.UID, user.getId(), RequestAttributeConst.ROLE, user.getRole()));
        response.setHeader("token", token);
        return ResultVO.success(user);
    }
}
