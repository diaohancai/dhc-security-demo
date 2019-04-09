package com.hancai.demo.user.rest;

import com.hancai.demo.user.dto.User;
import com.hancai.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    private UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @GetMapping
    public List<User> getUsers() {
        return userService.getUsersAll();
    }

    /**
     * 查询用户详细信息
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable String id) {
        //@PathVariable 将一个url路径占位符填充到函数参数中
        return userService.getUser(id);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping
    public User create(@RequestBody User user) {
        user = userService.create(user);
        return user;
    }

}
