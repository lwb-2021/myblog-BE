package com.github.lwb2021.myblog.controller;




import com.github.lwb2021.myblog.common.Result;
import com.github.lwb2021.myblog.common.util.JwtUtils;
import com.github.lwb2021.myblog.model.User;
import com.github.lwb2021.myblog.service.UserService;
import com.github.lwb2021.myblog.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;

/**
 * (Users)表控制层
 *
 * @author makejava
 * @since 2022-05-04 08:37:24
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    private UserService userService;

    private JwtUtils jwtUtils;

    @RequestMapping(value = "/login_from_user_center")
    public Result<?> login_from_user_center(@RequestBody HashMap<String, String> requestMap
    ,HttpServletResponse response) {
        String id = requestMap.get("id");
        String username = requestMap.get("username");
        String password = requestMap.get("password");
        Assert.notNull(id, "id不能为空");
        Assert.notNull(username, "username不能为空");
        Assert.notNull(password, "password不能为空");
        // TODO: uuid操作验证
        User user = userService.getById(Long.valueOf(id));
        User user2;
        if(user == null){
            user2 = new User(Long.parseLong(id) ,
                    username, "/image/default_avatar.png", 0, 0, 0, true);
            register_from_user_center_valid(user2);
            user = user2;
//            if(requestMap.containsKey("state")){
//                user2.setState(Integer.parseInt(requestMap.get("state")));
//            }
//            if(requestMap.containsKey("level")){
//                user2.setLevel(Integer.parseInt(requestMap.get("level")));
//            }
        }
        return login_from_user_center_valid(user, response);
    }

    @RequiresAuthentication
    @RequestMapping("/logout")
    public Result<?> logout(){
        SecurityUtils.getSubject().logout();
        return Result.succeed(0, "登出成功", null);
    }

    @RequiresAuthentication
    @RequestMapping("/verify")
    public Result<?> verify(){
        AccountProfile profile = (AccountProfile) SecurityUtils.getSubject().getPrincipal();
        User user = userService.getById(profile.getId());
        if(user==null){
            return Result.failed(-104, "用户未登录", null);
        }
        return Result.succeed(0, "用户已登录", user);
    }

    @RequestMapping("/get")
    public Result<?> getById(@RequestBody HashMap<String, String> requestMap){
        Long id = Long.parseLong(requestMap.get("id"));
        Assert.notNull(id, "id不能为空");
        User user = userService.getById(id);
        Assert.notNull(user, "用户不存在");
        return Result.succeed(0, "获取成功", user);
    }

    public Result<?> login_from_user_center_valid(@Valid User user, HttpServletResponse response){
        if(user.getState() == 1){
            response.setStatus(400);
            return Result.failed(-105, "账号被锁定", null);
        }else{
            String token = this.jwtUtils.generateToken(user.getId());
            response.addHeader("Authorization", token);
            response.setHeader("Access-Control-Expose-Headers", "Authorization");
            return Result.succeed(0, "转接成功", user);
        }
    }
    public void register_from_user_center_valid(@Valid User user){
        userService.save(user);
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

