package com.github.lwb2021.myblog.controller;




import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.github.lwb2021.myblog.common.Result;
import com.github.lwb2021.myblog.common.util.JwtUtils;
import com.github.lwb2021.myblog.model.Account;
import com.github.lwb2021.myblog.model.User;
import com.github.lwb2021.myblog.service.AccountService;
import com.github.lwb2021.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * (Users)表控制层
 *
 * @author makejava
 * @since 2022-05-04 08:37:24
 */
@RestController
@RequestMapping("/user")
public class UserCenterController {
    /**
     * 服务对象
     */
    private UserService userService;

    private AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }



    @RequestMapping(value = "/login")
    public SaResult login() {
        long id = StpUtil.getLoginIdAsLong();
        Account account = accountService.getById(id);
        User user = userService.getById(id);
        if (user == null){
            if (account == null)
                return SaResult.get(400, "用户名或密码错误", null);
            user = new User(id,
                    account.getUsername(),
                    "/image/default_avatar.png",
                    account.getLevel(),
                    0,
                    account.getState(),
                    true);
            userService.save(user);
        }
        return SaResult.get(200, "登录成功", user);
    }

    @RequestMapping("/verify")
    public SaResult verify(){
        long id = StpUtil.getLoginIdAsLong();
        User user = userService.getById(id);
        return SaResult.get(200, "用户已登录", user);
    }

    @RequestMapping("/get")
    public Result<?> getById(@RequestBody HashMap<String, String> requestMap){
        Long id = Long.parseLong(requestMap.get("id"));
        Assert.notNull(id, "id不能为空");
        User user = userService.getById(id);
        Assert.notNull(user, "用户不存在");
        return Result.succeed(0, "获取成功", user);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}

