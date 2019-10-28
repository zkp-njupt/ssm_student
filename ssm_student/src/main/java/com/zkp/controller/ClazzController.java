package com.zkp.controller;

import com.zkp.domain.Clazz;
import com.zkp.domain.Grade;
import com.zkp.page.Page;
import com.zkp.service.ClazzService;
import com.zkp.service.GradeService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/clazz")
public class ClazzController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private ClazzService clazzService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request){
        List<Grade> grades = gradeService.findAll();
        request.setAttribute("gradeList",grades);
        request.setAttribute("gradeListJSON", JSONArray.fromObject(grades));
        return "clazz/clazzList";
    }

    @RequestMapping("/clazz_list")
    public @ResponseBody Map<String,Object> getList(
            @RequestParam(value = "name",required = false,defaultValue = "") String name,
            @RequestParam(value = "gradeId",required = false) Long gradeId,
            Page page
    ){
        Map<String,Object> resultVal = new HashMap<>();
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("name","%"+name+"%");
        if(gradeId != null){
            queryMap.put("gradeId",gradeId);
        }
        queryMap.put("offset",page.getOffset());
        queryMap.put("pageSize",page.getRows());
        resultVal.put("rows",clazzService.findList(queryMap));
        resultVal.put("total",clazzService.findTotal(queryMap));

        return resultVal;
    }

    @RequestMapping("/add")
    public @ResponseBody Map<String,String> add(Clazz clazz){
        Map<String,String> map = new HashMap<>();

        if(clazzService.save(clazz) <= 0){
            map.put("type","error");
            map.put("msg","添加失败");
            return map;
        }
        map.put("type","success");
        map.put("msg","添加成功");
        return map;
    }

    @RequestMapping("/edit")
    public @ResponseBody Map<String,String> edit(Clazz clazz){
        Map<String,String> map = new HashMap<>();

        if(clazzService.update(clazz) <= 0){
            map.put("type","error");
            map.put("msg","修改失败");
            return map;
        }
        map.put("type","success");
        map.put("msg","修改信息成功");
        return map;
    }

    @RequestMapping("/delete")
    public @ResponseBody Map<String,String> delete(
            @RequestParam(value = "ids[]",required = true) Long[] ids
    ){
        Map<String,String> map = new HashMap<>();

        System.out.println("删除操作......");

        for (Long id : ids) {
            clazzService.delete(id);
        }
        map.put("type","success");
        map.put("msg","添加成功");
        return map;
    }


}
