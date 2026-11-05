package org.example.backendexamples.controller;

import lombok.RequiredArgsConstructor;
import org.example.backendexamples.entity.Address;
import org.example.backendexamples.service.UserService;
import org.example.backendexamples.vo.RequestAttributeConst;
import org.example.backendexamples.vo.ResultVO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("addresses")
    public ResultVO postAddress(@RequestBody Address address,
                                @RequestAttribute(RequestAttributeConst.UID) long uid) {
        address.setUserId(uid);
        userService.addAddress(address);
        return ResultVO.ok();
    }
}
