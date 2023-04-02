package com.security.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.security.config.SecurityConfig;
import com.security.domain.LoginUser;
import com.security.domain.User;
import com.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SecurityConfig securityConfig;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //根据用户名来查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(queryWrapper);
//        System.out.println("user:"+user.toString());
//        System.out.println("加密密码"+securityConfig.passwordEncoder().encode("1234"));
//        $2a$10$fCCydu4.dOaSZUDD3SFO6u2zQA3OomMJyOrcKsPudyr81gm6TMZp2
//        System.out.println("验证密码" + securityConfig.passwordEncoder().matches("1234", "$2a$10$fCCydu4.dOaSZUDD3SFO6u2zQA3OomMJyOrcKsPudyr81gm6TMZp2"));

        //未查询出就抛出异常
        if (Objects.isNull(user)){
            throw new RuntimeException("用户名或密码错误！");
        }

        //TODO 权限验证，添加到loginUser中

        //封装成UserDetails对象返回
        return new LoginUser(user);
    }
}
