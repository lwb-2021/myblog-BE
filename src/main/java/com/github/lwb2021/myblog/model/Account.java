package com.github.lwb2021.myblog.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 
 * @TableName accounts
 */
@TableName(value ="accounts")
@Data
public class Account implements Serializable {
    /**
     * 
     */
    @TableId
    private Long id;

    /**
     * 
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 
     */
    private Integer level;


    private Integer state;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Account(){}

    public Account(Long id, String email, String username, String password, Integer level, Integer state) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.level = level;
        this.state = state;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Account other = (Account) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getSimpleName());
        builder.append(" [");
        builder.append("Hash = ").append(hashCode());
        builder.append(", id=").append(id);
        builder.append(", email=").append(email);
        builder.append(", username=").append(username);
        builder.append(", password=").append(password);
        builder.append(", level=").append(level);
        builder.append(", serialVersionUID=").append(serialVersionUID);
        builder.append("]");
        return builder.toString();
    }
}