package com.github.lwb2021.myblog.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * (Blogs)表实体类
 *
 * @author makejava
 * @since 2022-05-04 11:42:19
 */
@TableName("blogs")
@Data
public class Blog{
    @TableId(value="id", type=IdType.AUTO)
    private Integer id;

    @NotBlank(message = "标题不能为空")
    private String title;

    @NotBlank(message = "标签不能为空")
    private String tags;

    @NotEmpty(message = "作者不能为空")
    private Long authorId;

    private Integer likeNumber;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;

    private Integer visit;

    private String content;

    @TableLogic
    private Integer deleted;

    public Blog(){}

    public Blog(String title, String tags, Long authorId, Integer likeNumber, Date created, Integer visit,
                String content) {
        this.title = title;
        this.authorId = authorId;
        this.likeNumber = likeNumber;
        this.created = created;
        this.visit = visit;
        this.content = content;
    }

}

