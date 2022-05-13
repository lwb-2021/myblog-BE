package com.github.lwb2021.myblog.mapper;

import com.github.lwb2021.myblog.model.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【accounts】的数据库操作Mapper
* @createDate 2022-05-03 18:40:56
* @Entity com.github.lwb2021.myblog.model.Accounts
*/
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

}




