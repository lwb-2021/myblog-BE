package com.github.lwb2021.myblog.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lwb2021.myblog.mapper.UserMapper;
import com.github.lwb2021.myblog.model.User;
import com.github.lwb2021.myblog.service.UserService;
import org.springframework.stereotype.Service;

/**
 * (Users)表服务实现类
 *
 * @author makejava
 * @since 2022-05-04 08:38:34
 */
@Service("usersService")
@DS("my_blog")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

