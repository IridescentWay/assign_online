package com.vere.assign_online.mapper;

import com.vere.assign_online.model.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper {
    @Select("select * from teacher")
    List<Teacher> queryAllTeachers();

    @Select("select * from teacher where account_id=#{accountId}")
    Teacher selectByAccountId(@Param("accountId") Integer accountId);

    int deleteByPrimaryKey(Integer id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);
}