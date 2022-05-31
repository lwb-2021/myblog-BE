package com.github.lwb2021.myblog.impl;

import cn.dev33.satoken.stp.StpInterface;
import com.github.lwb2021.myblog.model.Account;
import com.github.lwb2021.myblog.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StpInterfaceImpl implements StpInterface {
    private AccountService accountService;

    @Autowired
    public void setUserService(AccountService accountService) {
        this.accountService = accountService;
    }


    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        ArrayList<String> permissionList = new ArrayList<>();
        if(loginId instanceof Long){
            long id = (long) loginId;
            Account account = accountService.getById(id);
            if(account != null){
                int role = account.getRole();
                if(role >= 1){
                    permissionList.add("account:add");
                    permissionList.add("account:remove");
                    permissionList.add("account:ban");
                    permissionList.add("account:list");
                }
                if(role >= 2){
                    permissionList.add("admin:add");
                    permissionList.add("admin:remove");
                    permissionList.add("admin:list");
                    permissionList.add("admin:ban");
                }
                if(role >= 3){
                    permissionList.add("super:add");
                    permissionList.add("super:remove");
                    permissionList.add("super:list");
                    permissionList.add("super:ban");
                }
            }
        }
        return permissionList;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        ArrayList<String> roleList = new ArrayList<>();
        if(loginId instanceof Long){
            long id = (long) loginId;
            Account account = accountService.getById(id);
            if(account != null){
                int role = account.getRole();
                if(role >= 1){
                    roleList.add("admin");
                }
                if (role >= 2) {
                    roleList.add("super");
                }
                if (role >= 3) {
                    roleList.add("root");
                }
            }
        }
        return roleList;
    }
}
