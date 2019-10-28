package com.zkp.controller;


import com.zkp.domain.Grade;
import com.zkp.page.Page;
import com.zkp.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private GradeService service;

    @RequestMapping("/list")
    public String list(){
        return "grade/grade_list";
    }

    @RequestMapping("/grade_list")
    public @ResponseBody Map<String,Object> getList(
            @RequestParam(value = "name",required = false,defaultValue = "") String name,
            Page page
    ){
        Map<String,Object> resultVal = new HashMap<>();
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("name","%"+name+"%");
        queryMap.put("offset",page.getOffset());
        queryMap.put("pageSize",page.getRows());
        resultVal.put("rows",service.findList(queryMap));
        resultVal.put("total",service.findTotal(queryMap));

        return resultVal;
    }

    @RequestMapping("/add")
    public @ResponseBody Map<String,String> add(Grade grade){
        Map<String,String> map = new HashMap<>();

        if(service.save(grade) <= 0){
            map.put("type","error");
            map.put("msg","添加用户失败");
            return map;
        }
        map.put("type","success");
        map.put("msg","添加用户成功");
        return map;
    }

    @RequestMapping("/delete")
    public @ResponseBody Map<String,String> delete(
            @RequestParam(value = "ids[]",required = true) Long[] ids
    ){
        Map<String,String> map = new HashMap<>();

        for (Long id : ids) {
            service.delete(id);
        }
        map.put("type","success");
        map.put("msg","添加成功");
        return map;
    }

    @RequestMapping("/edit")
    public @ResponseBody Map<String,String> edit(Grade grade){
        Map<String,String> map = new HashMap<>();

        System.out.println();

        if(service.update(grade) <= 0){
            map.put("type","error");
            map.put("msg","修改失败");
            return map;
        }
        map.put("type","success");
        map.put("msg","修改信息成功");
        return map;
    }

}
