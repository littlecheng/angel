package com.weitao.service;

import com.weitao.entity.User;
import com.weitao.mapper.UserMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Service @Component public class UserServiceImpl implements UserService
{

    @Autowired
    UserMapper userMapper ;

    public User Sel(int id){
        return userMapper.Sel(id);
    }
}
