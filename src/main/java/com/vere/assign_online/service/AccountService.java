package com.vere.assign_online.service;


import com.vere.assign_online.common.exception.BadIdentityException;
import com.vere.assign_online.model.Account;
import com.vere.assign_online.model.Role;

import java.util.List;
import java.util.Map;

/**
 * ClassName:AccountService
 * Package:com.vere.demo.service
 * Description:
 *
 * @Date:2022/4/17 15:55
 * @Author:3046990030@qq.com
 */

public interface AccountService {
    int LOGIN = 1;
    int LOGOUT = 0;
    Account getAccountByUsername(String username);
    Account addAccount(Account account);
    Map<String, Object> studentLogin(String username, String password) throws BadIdentityException;
    Map<String, Object> teacherLogin(String username, String password) throws BadIdentityException;
    boolean updateAccount(Account account);
}
