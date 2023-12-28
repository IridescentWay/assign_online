package com.vere.assign_online.service.impl;

import com.vere.assign_online.common.exception.BadIdentityException;
import com.vere.assign_online.mapper.AccountMapper;
import com.vere.assign_online.model.Account;
import com.vere.assign_online.model.Role;
import com.vere.assign_online.model.Student;
import com.vere.assign_online.model.Teacher;
import com.vere.assign_online.service.AccountService;
import com.vere.assign_online.service.StudentService;
import com.vere.assign_online.service.TeacherService;
import com.vere.assign_online.utils.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:AccountServiceImpl
 * Package:com.vere.demo.service.impl
 * Description:
 *
 * @Date:2022/4/17 15:56
 * @Author:3046990030@qq.com
 */

@Service
public class AccountServiceImpl implements AccountService, UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Qualifier("accountServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /********  账户操作  ********/
    @Override
    public Account getAccountByUsername(String username) {
        return accountMapper.queryByName(username);
    }

    @Override
    public Account addAccount(Account account) {
        accountMapper.insert(account);
        return account;
    }

    /******** 学生服务 ********/
    @Override
    public Map<String, Object> studentLogin(String username, String password) throws BadCredentialsException, BadIdentityException {
        // 首先通过 userDetailService 获取用户信息
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        boolean allowable = false;  // 初始化允许访问为 false
        for (GrantedAuthority s : userDetails.getAuthorities()) {
            if (s.getAuthority().equals(Role.STUDENT)) {
                allowable = true;   // 用户权限列表中包含当前服务所需权限时设置允许访问为 true
            }
        }
        if (!allowable) {           // 根据允许访问的状态判定是否进行下一步操作
            throw new BadIdentityException("不得以学生身份登录！");
        }
        // 根据用户信息生成token
        String token = this.generateToken(username, password, userDetails);
        // 获取用户信息并返回
        Account account = accountMapper.queryByName(username);
        Student student = studentService.getStudentByAccountId(account.getId());
        System.out.println(student);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("tokenHead", tokenHead);
        dataMap.put("token", token);

        dataMap.put("accountId", account.getId());
        dataMap.put("username", account.getUsername());
        dataMap.put("email", account.getEmail());
        dataMap.put("phone", account.getPhone());

        dataMap.put("userId", student.getId());
        dataMap.put("realName", student.getName());
        dataMap.put("gender", student.getGender());
        dataMap.put("identityNumber", student.getStudentId());
        dataMap.put("major", student.getMajor());
        dataMap.put("faculty", student.getFaculty());
        return dataMap;
    }

    /******** 教师服务 *********/
    @Override
    public Map<String, Object> teacherLogin(String username, String password) throws BadIdentityException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        boolean allowable = false;
        for (GrantedAuthority s : userDetails.getAuthorities()) {
            if (s.getAuthority().equals(Role.TEACHER)) {
                allowable = true;
            }
        }
        if (!allowable) {
            throw new BadIdentityException("不得以老师身份登录！");
        }
        // 根据登录用户信息生成 token
        String token = this.generateToken(username, password, userDetails);
        // 获取用户信息并返回
        Account account = accountMapper.queryByName(username);
        Teacher teacher = teacherService.getTeacherByAccountId(account.getId());
        System.out.println(teacher);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("tokenHead", tokenHead);
        dataMap.put("token", token);

        dataMap.put("accountId", account.getId());
        dataMap.put("username", account.getUsername());
        dataMap.put("email", account.getEmail());
        dataMap.put("phone", account.getPhone());

        dataMap.put("userId", teacher.getId());
        dataMap.put("realName", teacher.getName());
        dataMap.put("gender", teacher.getGender());
        dataMap.put("identityNumber", teacher.getTeacherId());
        dataMap.put("major", teacher.getMajor());
        dataMap.put("faculty", teacher.getFaculty());

        return dataMap;
    }

    @Override
    public boolean updateAccount(Account account) {
        return accountMapper.updateByPrimaryKeySelective(account) == 1;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountMapper.queryByName(username);
        if (account == null) {
            throw new UsernameNotFoundException("账户不存在！");
        }
        account.setRoles(accountMapper.queryRolesById(account.getId()));
        return account;
    }

    /******** 公共代码 ********/
    private String generateToken(String username, String password, UserDetails userDetails) {
        System.out.println(password + " " + userDetails.getPassword());
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("密码不正确！");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 更新登录状态和最后登录时间
        accountMapper.updateLoginStatus(username, AccountService.LOGIN, System.currentTimeMillis());
        return jwtTokenUtil.generateToken(userDetails);
    }
}
