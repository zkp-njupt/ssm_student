package com.zkp.dao;

import com.zkp.domain.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;


public interface StudentDao {

    @Select("select * from student where username like #{username} or clazzId=#{clazz} limit #{offset},#{pageSize}")
    public List<Student> findList(Map<String,Object> queryMap);

    @Insert("insert into student(clazzId,sn,username,password,sex,photo,remark)" +
            " values(#{clazzId},#{sn},#{username},#{password},#{sex},#{photo},#{remark})")
    public int save(Student student);

    @Delete("delete from student where id=#{id}")
    public void delete(Long id);

    @Update("update student set clazzId=#{clazzId},username=#{username},password=#{password},sex=#{sex},photo=#{photo},remark=#{remark} where id=#{id}")
    public int update(Student student);
}
