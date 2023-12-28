package com.vere.assign_online.mapper;

import com.vere.assign_online.model.AccountRole;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountRole record);

    int insertSelective(AccountRole record);

    AccountRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountRole record);

    int updateByPrimaryKey(AccountRole record);
}