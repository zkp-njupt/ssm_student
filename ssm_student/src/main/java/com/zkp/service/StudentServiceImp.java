package com.zkp.service;

import com.zkp.dao.StudentDao;
import com.zkp.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImp implements StudentService{

    @Autowired
    private StudentDao dao;

    @Override
    public List<Student> findList(Map<String, Object> queryMap) {
        return dao.findList(queryMap);
    }

    @Override
    public int save(Student student) {
        return dao.save(student);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public int update(Student student) {
        return dao.update(student);
    }
}
