package com.hancai.demo.user.service;

import com.hancai.demo.user.dto.User;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserService {

    /**
     * 用户内存表
     */
    private List<User> users = new ArrayList<User>();

    public UserService() {
        // 初始化内存表
        users.add(new User("1", "刁汉财", "222", "admin", new Date()));
        users.add(new User("2", "张桥", "222", "admin", new Date()));
    }

    public List<User> getUsersAll() {
        return users;
    }

    public User getUser(String id) {
        for(User user : users) {
            if(user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public User create(User user) {
        if(StringUtils.isBlank(user.getId())) {
            user.setId("3");
        }
        users.add(user);
        return user;
    }
}
