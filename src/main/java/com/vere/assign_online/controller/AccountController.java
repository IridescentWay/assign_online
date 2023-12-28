package com.vere.assign_online.controller;

import com.vere.assign_online.common.exception.BadIdentityException;
import com.vere.assign_online.common.exception.BadPwdOrUsernameException;
import com.vere.assign_online.model.Account;
import com.vere.assign_online.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * ClassName:AccountController
 * Package:com.vere.demo.controller
 * Description:
 *
 * @Date:2022/4/17 15:04
 * @Author:3046990030@qq.com
 */

@RestController
public class AccountController {
    @Autowired
    AccountService accountService;

    String tokenKey = "token";

    /********  学生接口  ********/

    @PostMapping("/student/register")
    @ResponseBody
    Account studentRegister(@RequestBody Account account) {
        return accountService.addAccount(account);
    }

    @PostMapping("/student/login")
    @ResponseBody
    Map<String, Object> studentLogin(@RequestBody Account account) throws BadPwdOrUsernameException, BadIdentityException {
        Map<String, Object> data = accountService.studentLogin(account.getUsername(), account.getPassword());
        if (!data.containsKey(tokenKey)) {
            throw new BadPwdOrUsernameException("用户名或密码错误！");
        }
        return data;
    }

    /******** 教师接口 ********/
    @PostMapping("/teacher/login")
    @ResponseBody
    Map<String, Object> teacherLogin(@RequestBody Account account) throws BadPwdOrUsernameException, BadIdentityException {
        Map<String, Object> data = accountService.teacherLogin(account.getUsername(), account.getPassword());
        if (!data.containsKey(tokenKey)) {
            throw new BadPwdOrUsernameException("用户名或密码错误！");
        }
        return data;
    }

    /******** 共有接口 ********/
    @PutMapping("/account/update")
    public boolean updateAccount(@RequestBody Account account) {
        return accountService.updateAccount(account);
    }

    @RequestMapping(value = "/account/getAccountByUsername", method = RequestMethod.GET)
    @ResponseBody
    Account getAccountByUsername(@RequestParam String username) {
        return accountService.getAccountByUsername(username);
    }
}
