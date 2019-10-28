package com.zkp.dao;


import com.zkp.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {

    @Select("select * from user where username=#{username} and password=#{password}")
    public User findUser(User user);

    @Select("select * from user where username like #{username} limit #{offset},#{pageSize}")
    public List<User> findList(Map<String,Object> queryMap);

    @Insert("insert into user(username,password) values(#{username},#{password})")
    public int save(User user);

    @Update("update user set username=#{username},password=#{password} where id=#{id}")
    public int update(User user);

    @Select("select * from user where username=#{username}")
    public User findUserByName(String username);

    @Select("select count(id) from user where username like #{username}")
    public int findTotal(Map<String,Object> queryMap);

    @Delete("delete from user where id=#{id}")
    public void delete(Long id);

}
