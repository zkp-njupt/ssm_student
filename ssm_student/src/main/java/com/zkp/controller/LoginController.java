package com.zkp.controller;

import com.sun.org.glassfish.gmbal.ParameterNames;
import com.zkp.domain.User;
import com.zkp.service.UserService;
import com.zkp.utils.CpachaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService service;

    @RequestMapping("/check")
    public String check(){
        return "system/login";
    }

    //获取验证码
    @RequestMapping(value="/get_cpacha",method = RequestMethod.GET)
    public void get_cpacha(HttpServletRequest request,
                @RequestParam(value = "vl",defaultValue = "4",required = false) Integer vl,
                @RequestParam(value = "w",defaultValue = "98",required = false) Integer w,
                @RequestParam(value = "h",defaultValue = "33",required = false) Integer h,
                HttpServletResponse response){
        CpachaUtil cpachaUtil = new CpachaUtil(vl,w,h);
        String generatorVCode = cpachaUtil.generatorVCode();
        request.getSession().setAttribute("checkCode",generatorVCode);
        BufferedImage bufferedImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
        try {
            ImageIO.setUseCache(false);//更改缓存策略，不使用文件目录缓存，使用内存缓存
            ImageIO.write(bufferedImage,"gif",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //用户登录
    @RequestMapping("/userLogin")
    public @ResponseBody  Map<String,String> userLogin(String username, String password, String checkCode,HttpServletRequest request){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        User serviceUser = service.findUser(user);

        String checkCode1 = (String) request.getSession().getAttribute("checkCode");
        request.getSession().removeAttribute("checkCode");

        Map<String,String> map = new HashMap<>();

        if(checkCode1.equalsIgnoreCase(checkCode) && checkCode != null){
            //验证码正确,判断用户是否存在
            if(serviceUser != null){
                map.put("type","success");
                map.put("msg","登录成功");
                System.out.println("成功");
                request.getSession().setAttribute("user",user);
            }else{
                System.out.println("错误");
                map.put("type","error");
                map.put("msg","用户名或密码错误");
            }
        }else{
            //验证码错误
            System.out.println("验证码错误");
            map.put("type","error");
            map.put("msg","验证码错误");
        }

        return map;
    }

    @RequestMapping("/index")
    public String index(){
        return "system/index";
    }

    @RequestMapping("/login_out")
    public String loginOut(){
        return "system/login";
    }
}














