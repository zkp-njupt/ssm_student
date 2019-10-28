package com.zkp.service;

import com.zkp.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    public User findUser(User user);

    public List<User> findList(Map<String,Object> queryMap);

    public int save(User user);

    public int update(User user);

    public User findUserByName(String username);

    public int findTotal(Map<String,Object> queryMap);

    public void delete(Long id);

}
