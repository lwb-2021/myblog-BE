package com.github.lwb2021.myblog.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * (Users)表实体类
 *
 * @author makejava
 * @since 2022-05-04 08:38:18
 */
@Data
@TableName("users")
public class User {

    private Long id;

    @NotBlank(message = "用户名不能为空")
    private String username;
    
    private String avatar;

    private Integer level;
    
    private Integer points;
    
    private Integer state;
    
    private Date created;

    @TableField("last_login")
    private Date lastLogin;

    private int role;

    public User(){}

    public User(long id, String username, String avatar, Integer level, Integer points, Integer state, boolean create) {
        this.id = id;
        this.username = username;
        this.avatar = avatar;
        this.level = level;
        this.points = points;
        this.state = state;
        this.role = 0;
        if(create){
            this.created = new Date();
        }
         this.lastLogin = new Date();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

}

