package com.github.lwb2021.myblog.shiro;

import com.github.lwb2021.myblog.model.User;
import lombok.Data;

@Data
public class AccountProfile {

    private Long id;

    private String username;

    private String avatar;

    private String email;

    private int role;

    public AccountProfile() {

    }

    public AccountProfile(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.avatar = user.getAvatar();
        this.role = user.getRole();
    }

    public AccountProfile(Long id, String username, String avatar, String email, int role) {
        this.id = id;
        this.username = username;
        this.avatar = avatar;
        this.email = email;
        this.role = role;
    }
}