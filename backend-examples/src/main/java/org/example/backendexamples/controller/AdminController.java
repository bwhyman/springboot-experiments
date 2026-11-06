package org.example.backendexamples.controller;

import lombok.RequiredArgsConstructor;
import org.example.backendexamples.entity.User;
import org.example.backendexamples.service.UserService;
import org.example.backendexamples.vo.ResultVO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @PostMapping("users")
    public ResultVO postUser(@RequestBody User user) {
        userService.addUser(user);
        return ResultVO.ok();
    }

    @GetMapping("users/{uid}")
    public ResultVO getUserAddresses(@PathVariable long uid) {
        return ResultVO.success(userService.listUserAddresses(uid));
    }
    //分页获取全部用户
    @GetMapping("users/pages/{pnumber}/{psize}")
    public ResultVO getUsers(@PathVariable int pnumber, @PathVariable int psize) {
        return ResultVO.success(userService.listUsers(pnumber, psize));
    }
}
