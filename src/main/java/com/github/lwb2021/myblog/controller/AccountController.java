package com.github.lwb2021.myblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.lwb2021.myblog.common.Result;
import com.github.lwb2021.myblog.common.exceptions.CustomHttpException;
import com.github.lwb2021.myblog.common.util.SnowflakeIDWorker;
import com.github.lwb2021.myblog.model.Account;
import com.github.lwb2021.myblog.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/account")
public class AccountController {
    AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/login")
    public Result<?> login(@RequestBody HashMap<String, String> requestMap) {
        String username = requestMap.get("username");
        String password = requestMap.get("password");
        Assert.notNull(username, "username不能为空");
        Assert.notNull(password, "password不能为空");
        QueryWrapper<Account> accountsQueryWrapper = new QueryWrapper<>();
        accountsQueryWrapper.eq("username", username);
        accountsQueryWrapper.eq("password", password);
        Account account = accountService.getOne(accountsQueryWrapper);
        return login_valid(account);
    }
    @RequestMapping(value = "/register")
    public Result<?> register(@RequestBody HashMap<String, String> requestMap) {
        String email = requestMap.get("email");
        String username = requestMap.get("username");
        String password = requestMap.get("password");
        Assert.notNull(email, "email不能为空");
        Assert.notNull(username, "username不能为空");
        Assert.notNull(password, "password不能为空");
        Account account = new Account(SnowflakeIDWorker.getInstance().nextID() ,
                email, username, password, 0, 0);
        QueryWrapper<Account> accountQueryWrapper = new QueryWrapper<>();
        accountQueryWrapper.eq("email", email);
        if(accountService.count(accountQueryWrapper) > 0){
            throw new CustomHttpException(400, Result.failed(-102, "注册失败，账号已存在", null));
        }else {
            register_valid(account);
            return Result.succeed(0, "注册成功", account);
        }

    }
    public void register_valid(@Valid Account account){
        accountService.save(account);
    }
    public Result<?> login_valid(@Valid Account account){
        if(account == null){
            throw new CustomHttpException(400, Result.failed(-101, "登录失败，账号或密码错误", null));
        }else {
            return Result.succeed(0, "登录成功", account);
        }
    }
}
