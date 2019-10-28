package com.zkp.service;


import com.zkp.dao.GradeDao;
import com.zkp.domain.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("gradeService")
public class GradeServiceImp implements GradeService{

    @Autowired
    private GradeDao dao;

    @Override
    public List<Grade> findList(Map<String, Object> queryMap) {
        return dao.findList(queryMap);
    }

    @Override
    public int findTotal(Map<String, Object> queryMap) {
        return dao.findTotal(queryMap);
    }

    @Override
    public int save(Grade grade) {
        return dao.save(grade);
    }

    @Override
    public int delete(Long id) {
        return dao.delete(id);
    }

    @Override
    public int update(Grade grade) {
        return dao.update(grade);
    }

    @Override
    public List<Grade> findAll() {
        return dao.findAll();
    }
}
