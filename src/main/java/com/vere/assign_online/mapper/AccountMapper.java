package com.vere.assign_online.mapper;

import com.vere.assign_online.model.Account;
import com.vere.assign_online.model.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountMapper {
    @Select("select * from account where username=#{username}")
    Account queryByName(@Param("username") String username);

    @Update("update account set status=#{logStatus}, last_login_time=#{lastLoginTime} where username=#{username}")
    boolean updateLoginStatus(@Param("username") String username,
                           @Param("logStatus") Integer status,
                           @Param("lastLoginTime") Long lastLoginTime);

    List<Role> queryRolesById(Integer id);

    /******** 自动生成的 ********/
    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}