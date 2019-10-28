package com.zkp.service;

import com.zkp.dao.ClazzDao;
import com.zkp.domain.Clazz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ClazzServiceImp implements ClazzService{

    @Autowired
    private ClazzDao dao;

    @Override
    public List<Clazz> findList(Map<String, Object> queryMap) {
        return dao.findList(queryMap);
    }

    @Override
    public int findTotal(Map<String, Object> queryMap) {
        return dao.findTotal(queryMap);
    }

    @Override
    public int save(Clazz clazz) {
        return dao.save(clazz);
    }

    @Override
    public int delete(Long id) {
        return dao.delete(id);
    }

    @Override
    public int update(Clazz clazz) {
        return dao.update(clazz);
    }

    @Override
    public List<Clazz> findAll() {
        return dao.findAll();
    }
}
