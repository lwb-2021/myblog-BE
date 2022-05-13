package com.github.lwb2021.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lwb2021.myblog.model.Blog;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Blogs)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-04 11:42:17
 */
@Mapper
public interface BlogMapper extends BaseMapper<Blog> {
}

