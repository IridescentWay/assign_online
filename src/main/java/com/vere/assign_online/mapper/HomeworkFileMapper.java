package com.vere.assign_online.mapper;

import com.vere.assign_online.model.HomeworkFile;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkFileMapper {
    @ResultType(String.class)
    @Select("select file_url from homework_file where homework_id=#{homeworkId} and student_id=#{studentId}")
    List<String> queryPersonalFile(@Param("studentId") Integer studentId, @Param("homeworkId") Integer homeworkId);

    @Select("select count(*) from homework_file where student_id=#{studentId} and homework_id=#{homeworkId}")
    boolean searchStudent(@Param("studentId") Integer studentId, @Param("homeworkId") Integer homeworkId);

    int deleteByPrimaryKey(Integer id);

    int insert(HomeworkFile record);

    int insertSelective(HomeworkFile record);

    HomeworkFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HomeworkFile record);

    int updateByPrimaryKey(HomeworkFile record);
}