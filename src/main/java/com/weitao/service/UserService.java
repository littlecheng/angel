package com.weitao.service;

import com.weitao.entity.User;
import com.weitao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper ;

    public User Sel(int id){
        return userMapper.Sel(id);
    }
}
