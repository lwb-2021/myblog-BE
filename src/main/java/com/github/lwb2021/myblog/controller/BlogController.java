package com.github.lwb2021.myblog.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.spring.SpringMVCUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lwb2021.myblog.common.Result;
import com.github.lwb2021.myblog.model.Blog;
import com.github.lwb2021.myblog.service.BlogService;
import com.github.lwb2021.myblog.service.UserService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;


/**
 * (Blogs)表控制层
 *
 * @author makejava
 * @since 2022-05-04 11:42:13
 */
@Slf4j
@RestController
@RequestMapping("/blog")
public class BlogController {
    private BlogService blogService;

    @Autowired
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }


    @RequestMapping("/total")
    public Result<?> total(@RequestParam(defaultValue ="") String keywords){
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        if(!keywords.equals("")){
            for(String keyword : keywords.split(" ")){
                wrapper.like("title", keyword).or().like("content", keyword);
            }
        }
        return Result.succeed(0, "查询成功", blogService.count(wrapper));
    }

    @RequestMapping(value = "/list")
    public Result<?> list(@RequestParam(defaultValue = "1") Integer currentPage,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(defaultValue = "") String keywords,
                        @RequestParam(defaultValue = "") String tags){
        Page<Blog> page = new Page<>(currentPage, pageSize);
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        if(!keywords.isBlank()){
            for(String keyword : keywords.split(" ")){
                wrapper.like("title", keyword).or().like("content", keyword);
            }
        }
        if(!tags.isBlank()){
            wrapper.like("tags", tags);
        }
        wrapper.orderByAsc("created");
        IPage<Blog> ipage = blogService.page(page, wrapper);
        return Result.succeed(0, "查询成功", ipage);
    }

    @RequestMapping(value = "/get")
    public Result<?> getBlog(@RequestBody HashMap<String, String> requestMap){
        int id = Integer.parseInt(requestMap.get("id"));
        return Result.succeed(0, "读取成功", getBlogById(id));
    }
    public Blog getBlogById(Integer id){
        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "博客不存在或已删除");
        return blog;
    }

    @SaCheckLogin
    @RequestMapping(value = "/create")
    public SaResult createBlog(@RequestBody HashMap<String, String> requestMap){
        String newTitle = requestMap.get("newTitle");
        String newBlogContent = requestMap.get("newBlogContent");
        String tags = requestMap.get("tags");

        Assert.notNull(newTitle, "title不能为空");
        Assert.notNull(newBlogContent, "blogContent不能为空");

        Assert.isTrue(!newTitle.trim().isEmpty(), "title不能为空");
        Assert.isTrue(!newBlogContent.trim().isEmpty(), "blogContent不能为空");

        log.debug("Parameters Are OK, Finding User");

        long id = StpUtil.getLoginIdAsLong();
        log.info("User Found, ID:" + id);

        QueryWrapper<Blog> blogQueryWrapper = new QueryWrapper<>();
        blogQueryWrapper.eq("title", newTitle);

        Assert.isNull(blogService.getOne(blogQueryWrapper, false), "请勿编写相同题目的博客");

        Blog blog = new Blog(newTitle, tags, id, 0, new Date(), 0, newBlogContent);
        blogService.save(blog);

        return SaResult.get(200, "上传成功", blog);
    }
    @SaCheckRole
    @RequestMapping(value = "/edit")
    public SaResult editBlog(@RequestBody HashMap<String, String> requestMap){
        String newBlogContent = requestMap.get("newBlogContent");
        String newTitle = requestMap.get("newTitle");
        String tags = requestMap.get("tags");
        Integer blogId = Integer.parseInt(requestMap.get("blogId"));
        Assert.notNull(newBlogContent, "newBlogContent不能为空");
        Assert.isTrue(!newBlogContent.trim().isEmpty(), "newBlogContent不能为空");
        Assert.notNull(newTitle, "newTitle不能为空");
        Assert.isTrue(!newTitle.trim().isEmpty(), "newTitle不能为空");
        Assert.notNull(tags, "tags不能为空");
        Assert.isTrue(!tags.trim().isEmpty(), "tags不能为空");
        Blog blog = getBlogById(blogId);

        log.debug("Parameters Are OK, Finding User");
        SaResult result;
        if ((result = checkIsAuthor(blog, "您不是博客的作者，无法修改")) != null) {
            return result;
        }
        blog.setContent(newBlogContent);
        blog.setTitle(newTitle);
        blog.setTags(tags);
        blogService.saveOrUpdate(blog);

        return SaResult.get(200, "修改成功", blog);
    }

    public SaResult checkIsAuthor(Blog blog, String message){

        long id = StpUtil.getLoginIdAsLong();
        log.info("User Found, ID:" + id);

        if(id != blog.getAuthorId()){
            SpringMVCUtil.getResponse().setStatus(400);
            return SaResult.get(400, message, null);
        }
        return null;
    }
//    @RequiresAuthentication
    @RequestMapping(value = "/delete")
    public SaResult deleteBlog(@RequestBody HashMap<String, String> requestMap){
        Integer blogId = Integer.parseInt(requestMap.get("id"));

        Blog blog = getBlogById(blogId);
        log.debug("Parameters Are OK, Finding User");
        SaResult result;
        if ((result = checkIsAuthor(blog, "您不是博客的作者，无法删除")) != null) {
            return result;
        }

        blogService.removeById(blogId);
        return SaResult.get(200, "删除成功", null);
    }
}

