package com.weitao.service;

import com.weitao.entity.User;

import java.util.List;

public interface UserService
{
    User Sel(int id);

    void Save(User user);

    List<User> SelectAll();
}
