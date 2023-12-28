package com.vere.assign_online.mapper;

import com.vere.assign_online.model.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {
    @Select("select * from student")
    List<Student> queryAllStudents();

    // @ResultMap("BaseResultMap")
    @Select("select * from student where student_id=#{studentId} and name=#{studentName}")
    Student queryStudent(@Param("studentId") Long studentId, @Param("studentName") String studentName);

    @Select("select * from student where account_id=#{accountId}")
    Student selectByAccountId(@Param("accountId") Integer accountId);

    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}