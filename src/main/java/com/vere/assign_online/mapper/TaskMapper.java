package com.vere.assign_online.mapper;

import com.vere.assign_online.model.Task;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TaskMapper {
    @Select("select * from task where homework_id=#{homeworkId}")
    @ResultType(Map.class)
    List<Map<String, Object>> queryByHomeworkId(@Param("homeworkId") Integer homeworkId);

    @Delete("delete from task where homework_id=#{homeworkId}")
    int deleteByHomeworkId(Integer homeworkId);

    int deleteByPrimaryKey(Integer id);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);
}