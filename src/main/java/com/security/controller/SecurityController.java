package com.security.controller;


import com.security.domain.ResponseResult;
import com.security.domain.User;
import com.security.mapper.MenuMapper;
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

    @Autowired
    private MenuMapper menuMapper;

    @PostMapping("/user/login")
    public ResponseResult hello(@RequestBody User user){
//        userService.loadUserByUsername(username);
        return userService.login(user);
    }

    @GetMapping("/hello")
//    @PreAuthorize("hasAuthoritySG('system:menu:list111')")
    public String helloworld(){
        return "测试成功！";
    }

    @GetMapping("/user/logout")
    public ResponseResult logout(){
        return userService.logout();
    }


    @GetMapping("/test")
    public void test(){
        System.out.println(menuMapper.selectPermsByUserId(2L));
    }

    @GetMapping("/testCors")
    @PreAuthorize("@ex.hasAuthoritySG('system:menu:list')")
    public ResponseResult testCors(){
        return new ResponseResult(200,"testCors访问成功");
    }
}
