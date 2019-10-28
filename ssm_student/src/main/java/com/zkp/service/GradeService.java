package com.zkp.service;

import com.zkp.domain.Grade;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Repository
public interface GradeService {

    public List<Grade> findList(Map<String,Object> queryMap);

    public int findTotal(Map<String, Object> queryMap);

    public int save(Grade grade);

    public int delete(Long id);

    public int update(Grade grade);

    public List<Grade> findAll();
}
