package com.github.lwb2021.myblog.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lwb2021.myblog.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Users)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-04 08:38:14
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}

