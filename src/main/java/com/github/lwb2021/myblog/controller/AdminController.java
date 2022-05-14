package com.github.lwb2021.myblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lwb2021.myblog.common.Result;
import com.github.lwb2021.myblog.common.util.JwtUtils;
import com.github.lwb2021.myblog.model.User;
import com.github.lwb2021.myblog.service.UserService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RequestMapping(value = "/admin")
@RestController
public class AdminController {


    private UserService userService;
    private JwtUtils jwtUtils;

    @RequiresRoles("admin")
    @RequestMapping("/lock")
    public Result<?> lockById(@RequestBody HashMap<String, String> requestMap){
        Long id = Long.parseLong(requestMap.get("id"));
        Assert.notNull(id, "id不能为空");
        User user = userService.getById(id);
        user.setState(1);
        userService.saveOrUpdate(user);
        return Result.succeed(0, "锁定成功", user);
    }
    @RequestMapping("/unlock")
    @RequiresRoles("admin")
    public Result<?> unlockById(@RequestBody HashMap<String, String> requestMap){
        Long id = Long.parseLong(requestMap.get("id"));
        Assert.notNull(id, "id不能为空");
        User user = userService.getById(id);
        user.setState(0);
        userService.saveOrUpdate(user);
        return Result.succeed(0, "解锁成功", user);
    }

    @RequestMapping(value = "/list")
    @RequiresRoles("admin")
    public Result<?> list(@RequestParam(defaultValue = "1") Integer currentPage,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          @RequestParam(defaultValue ="") String keywords){
        Page<User> page = new Page<>(currentPage, pageSize);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if(!keywords.equals("")){
            for(String keyword : keywords.split(" ")){
                wrapper.like("username", keyword);
            }
        }
        wrapper.orderByAsc("username");
        IPage<User> ipage = userService.page(page, wrapper);
        return Result.succeed(0, "查询成功", ipage);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setJwtUtils(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }
}
