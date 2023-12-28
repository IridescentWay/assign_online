package com.vere.assign_online.mapper;

import com.vere.assign_online.model.HomeworkGuideline;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkGuidelineMapper {
    @ResultType(String.class)
    @Select("select file_url from homework_guideline where homework_id=#{homeworkId}")
    List<String> queryGuideline(@Param("homeworkId") Integer homeworkId);

    int deleteByPrimaryKey(Integer id);

    int insert(HomeworkGuideline record);

    int insertSelective(HomeworkGuideline record);

    HomeworkGuideline selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HomeworkGuideline record);

    int updateByPrimaryKey(HomeworkGuideline record);
}