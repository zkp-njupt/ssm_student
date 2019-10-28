package com.zkp.service;

import com.zkp.dao.UserDao;
import com.zkp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("service")
public class UserServiceImp implements UserService{

    @Autowired
    private UserDao dao;

    @Override
    public User findUser(User user) {
        User user1 = null;
        try{
            user1 = dao.findUser(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user1;
    }

    @Override
    public List<User> findList(Map<String,Object> queryMap) {
        return dao.findList(queryMap);
    }

    @Override
    public int save(User user) {
        return dao.save(user);
    }

    @Override
    public int update(User user) {
        return dao.update(user);
    }

    @Override
    public User findUserByName(String username) {
        return dao.findUserByName(username);
    }

    @Override
    public int findTotal(Map<String, Object> queryMap) {
        return dao.findTotal(queryMap);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }
}
