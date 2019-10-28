package com.zkp.service;

import com.zkp.domain.Clazz;
import com.zkp.domain.Grade;

import java.util.List;
import java.util.Map;


public interface ClazzService {

    public List<Clazz> findList(Map<String, Object> queryMap);

    public int findTotal(Map<String, Object> queryMap);

    public int save(Clazz clazz);

    public int delete(Long id);

    public int update(Clazz clazz);

    public List<Clazz> findAll();
}
