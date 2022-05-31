package com.github.lwb2021.myblog.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lwb2021.myblog.mapper.BlogMapper;
import com.github.lwb2021.myblog.model.Blog;
import org.springframework.stereotype.Service;
import com.github.lwb2021.myblog.service.BlogService;

/**
 * (Blogs)表服务实现类
 *
 * @author makejava
 * @since 2022-05-04 11:42:20
 */
@Service
@DS("my_blog")
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
}

