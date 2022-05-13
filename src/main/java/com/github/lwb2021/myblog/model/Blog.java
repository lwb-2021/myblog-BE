package com.github.lwb2021.myblog.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;

/**
 * (Blogs)表实体类
 *
 * @author makejava
 * @since 2022-05-04 11:42:19
 */
@TableName("blogs")
public class Blog{
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;

    @NotEmpty(message = "标题不能为空")
    private String title;

    @NotEmpty(message = "作者不能为空")
    private Long authorId;

    private Integer likeNumber;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;

    private Integer visit;

    private String content;

    public Blog(){}

    public Blog(String title, Long authorId, Integer likeNumber, Date created, Integer visit,
                String content) {
        this.title = title;
        this.authorId = authorId;
        this.likeNumber = likeNumber;
        this.created = created;
        this.visit = visit;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Integer getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(Integer likeNumber) {
        this.likeNumber = likeNumber;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getVisit() {
        return visit;
    }

    public void setVisit(Integer visit) {
        this.visit = visit;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

