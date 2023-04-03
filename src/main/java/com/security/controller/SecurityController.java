package com.security.controller;


import com.security.domain.ResponseResult;
import com.security.domain.User;
import com.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SecurityController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public ResponseResult hello(@RequestBody User user){
//        userService.loadUserByUsername(username);
        return userService.login(user);
    }

    @GetMapping("/hello")
    @PreAuthorize("hasAnyAuthority('test')")
    public String helloworld(){
        return "测试成功！";
    }

    @GetMapping("/user/logout")
    public ResponseResult logout(){
        return userService.logout();
    }


}
