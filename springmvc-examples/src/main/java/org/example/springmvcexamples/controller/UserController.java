package org.example.springmvcexamples.controller;

import org.example.springmvcexamples.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    @GetMapping("info")
    public ResultVO getInfo(@RequestAttribute("uid") long uid) {
        return ResultVO.success(Map.of("uid", uid));
    }
}
