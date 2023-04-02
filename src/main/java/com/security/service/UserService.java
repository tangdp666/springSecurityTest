package com.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.security.domain.ResponseResult;
import com.security.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2023-04-02 15:31:30
 */
public interface UserService extends IService<User> {
    ResponseResult logout();

    ResponseResult login(User user);

//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}

