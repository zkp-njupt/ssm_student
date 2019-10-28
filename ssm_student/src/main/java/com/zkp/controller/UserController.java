package com.zkp.controller;


import com.zkp.dao.UserDao;
import com.zkp.domain.User;
import com.zkp.page.Page;
import com.zkp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, HttpServletResponse response){

        return "user/user_list";
    }

    @RequestMapping("/add")
    public @ResponseBody Map<String,String> add(String username,String password){
        Map<String,String> map = new HashMap<>();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User user1 = service.findUserByName(username);
        if(user1 != null){
            map.put("type","error");
            map.put("msg","用户名已存在！");
            return map;
        }
        if(service.save(user) <= 0){
            map.put("type","error");
            map.put("msg","添加失败！");
            return map;
        }
        map.put("type","success");
        map.put("msg","添加成功！");
        return map;
    }

    //显示用户信息
    @RequestMapping("/get_list")
    public @ResponseBody Map<String,Object> getList(
            @RequestParam(value = "username",required = false,defaultValue = "") String username,
            Page page
    ){
        Map<String,Object> resultVal = new HashMap<>();
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("username","%"+ username +"%");
        queryMap.put("offset",page.getOffset());//偏移量
        queryMap.put("pageSize",page.getRows());//每页显示多少条数据
        resultVal.put("rows",service.findList(queryMap));//显示的数据
        resultVal.put("total",service.findTotal(queryMap));//总共多少条数据

        return resultVal;
    }

    @RequestMapping("/edit")
    public @ResponseBody Map<String,String> edit(User user){
        Map<String,String> map = new HashMap<>();
        if(service.update(user) <= 0){
            map.put("type","error");
            map.put("msg","修改失败！");
            return map;
        }
        map.put("type","success");
        map.put("msg","修改成功！");
        return map;
    }

    /*
    * 删除用户
    * */
    @RequestMapping("/delete")
    public @ResponseBody Map<String,String> delete(
            @RequestParam(value = "ids[]",required = true) Long[] ids
    ){
        Map<String,String> map = new HashMap<>();
        for (Long id : ids) {
            service.delete(id);
        }
        map.put("type","success");
        map.put("msg","删除用户成功！");
        return map;
    }
}












