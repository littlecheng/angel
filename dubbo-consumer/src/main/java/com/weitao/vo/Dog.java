package com.weitao.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.weitao.entity.User;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @title: Dog
 * @projectName angel
 * @description: TODO
 * @author Administrator
 * @date 2020/5/720:47
 */
@Component
@ConfigurationProperties(prefix = "dog") public class Dog
{

    private String name;
    private String age;
    private boolean sex;
    @JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd")
    private Date birthday;
    private Map<String,Object> map;
    private User user;


    private List<String> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Dog{" + "name='" + name + '\'' + ", age='" + age + '\'' + ", sex=" + sex + ", birthday=" + birthday
            + ", map=" + map + ", user=" + user + ", list=" + list + '}';
    }
}
