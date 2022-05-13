package com.github.lwb2021.myblog.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lwb2021.myblog.model.Account;
import com.github.lwb2021.myblog.service.AccountService;
import com.github.lwb2021.myblog.mapper.AccountMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【accounts】的数据库操作Service实现
* @createDate 2022-05-03 18:40:56
*/
@Service
@DS("user_center")
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account>
    implements AccountService {

}




