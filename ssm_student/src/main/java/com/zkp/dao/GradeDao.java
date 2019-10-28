package com.zkp.dao;

import com.zkp.domain.Grade;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface GradeDao {

    @Select("select * from grade where name like #{name} limit #{offset},#{pageSize}")
    public List<Grade> findList(Map<String,Object> queryMap);

    @Select("select count(id) from grade where name like #{name}")
    public int findTotal(Map<String, Object> queryMap);

    @Insert("insert into grade(name,remark) values(#{name},#{remark})")
    public int save(Grade grade);

    @Delete("delete from grade where id=#{id}")
    public int delete(Long id);

    @Update("update grade set name=#{name},remark=#{remark} where id=#{id}")
    public int update(Grade grade);

    @Select("select * from grade")
    public List<Grade> findAll();
}
