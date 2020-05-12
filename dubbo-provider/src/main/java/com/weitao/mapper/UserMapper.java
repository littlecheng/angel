package com.weitao.mapper;

import com.weitao.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository public interface UserMapper
{

    User Sel(int id);

    void Save(@Param("user") User user);

    List<User> SelectAll();
}
