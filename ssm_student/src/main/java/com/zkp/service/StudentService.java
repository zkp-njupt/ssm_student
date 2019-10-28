package com.zkp.service;

import com.zkp.domain.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {


    public List<Student> findList(Map<String,Object> queryMap);

    public int save(Student student);

    public void delete(Long id);

    public int update(Student student);
}
