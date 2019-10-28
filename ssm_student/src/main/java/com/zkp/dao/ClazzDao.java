package com.zkp.dao;

import com.zkp.domain.Clazz;
import com.zkp.domain.Grade;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface ClazzDao {

    @Select("select * from clazz where name like #{name} or gradeId=#{gradeId} limit #{offset},#{pageSize}")
    public List<Clazz> findList(Map<String, Object> queryMap);

    @Select("select count(id) from clazz where name like #{name}")
    public int findTotal(Map<String, Object> queryMap);

    @Insert("insert into clazz(name,gradeId,remark) values(#{name},#{gradeId},#{remark})")
    public int save(Clazz clazz);

    @Delete("delete from clazz where id=#{id}")
    public int delete(Long id);

    @Update("update clazz set name=#{name},gradeId=#{gradeId},remark=#{remark} where id=#{id}")
    public int update(Clazz clazz);

    @Select("select * from clazz")
    public List<Clazz> findAll();
}
