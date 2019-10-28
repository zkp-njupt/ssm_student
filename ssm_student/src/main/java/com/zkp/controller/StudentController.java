package com.zkp.controller;


import com.zkp.domain.Clazz;
import com.zkp.domain.Student;
import com.zkp.page.Page;
import com.zkp.service.ClazzService;
import com.zkp.service.StudentService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private ClazzService clazzService;

    @Autowired
    private StudentService studentService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request){

        List<Clazz> clazzList = clazzService.findAll();

        request.setAttribute("clazzList",clazzList);
        request.setAttribute("clazzListJSON", JSONArray.fromObject(clazzList));

        return "student/studentList";
    }

    @RequestMapping("/get_list")
    public @ResponseBody Map<String,Object> getList(
            @RequestParam(value = "username",required = false,defaultValue = "") String username,
            @RequestParam(value = "clazzId",required = false) Long clazzId,
            Page page
    ){
        Map<String,Object> resultVal = new HashMap<>();
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("username","%"+ username +"%");
        if(clazzId != null){
            queryMap.put("clazzId",clazzId);
        }
        queryMap.put("offset",page.getOffset());
        queryMap.put("pageSize",page.getRows());

        List<Student> studentList = studentService.findList(queryMap);
        resultVal.put("rows",studentList);
        resultVal.put("total",10);

        return resultVal;
    }

    @RequestMapping("/add")
    public @ResponseBody Map<String,String> add(Student student){
        Map<String,String> resultVal = new HashMap<>();

        System.out.println(student.toString());
        student.setSn("s" + new Date().getTime());

        if(studentService.save(student) <= 0){
            resultVal.put("type","error");
            resultVal.put("msg","添加失败");
            return resultVal;
        }
        resultVal.put("type","success");
        resultVal.put("msg","用户添加成功!!");
        return resultVal;
    }

    //上传图片
    @RequestMapping("/upload_photo")
    public @ResponseBody Map<String,String> uploadPhoto(HttpServletRequest request,MultipartFile photo) throws IOException {
        System.out.println("上传图片中......");
        Map<String,String> resultVal = new HashMap<>();

        //判断图片大小
        if(photo.getSize() > 10485760){
            resultVal.put("type","error");
            resultVal.put("msg","上传图片过大，请上传小于10M的图片");
            return resultVal;
        }
        String originalFilename = photo.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length());
        //判断图片格式
        if(!"jpg,png,gif,jpeg".contains(suffix.toLowerCase())){
            resultVal.put("type","error");
            resultVal.put("msg","上传图片格式不正确，请上传jpg,png,gif,jpeg格式的图片");
            return resultVal;
        }
        //获取文件真实路径
        String path = request.getSession().getServletContext().getRealPath("/upload");
        System.out.println(path);
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        String fileName = photo.getName() + new Date().getTime() + "." + suffix;
        photo.transferTo(new File(path,fileName));

        resultVal.put("type","success");
        resultVal.put("msg","图片上传成功！！");
        resultVal.put("src",request.getSession().getServletContext().getContextPath()+"/upload/"+fileName);
        return resultVal;
    }

    @RequestMapping("/delete")
    public @ResponseBody Map<String,String> delete(
            @RequestParam(value = "ids[]",required = true) Long[] ids
    ){
        Map<String,String> resultVal = new HashMap<>();
        for (Long id : ids) {
            studentService.delete(id);
        }
        resultVal.put("type","success");
        resultVal.put("msg","删除成功");
        return resultVal;
    }

    @RequestMapping("/edit")
    public @ResponseBody Map<String,String> update(Student student){
        Map<String,String> resultVal = new HashMap<>();

        if(studentService.update(student) <= 0){
            resultVal.put("type","error");
            resultVal.put("msg","修改失败");
            return resultVal;
        }
        resultVal.put("type","success");
        resultVal.put("msg","用户修改成功!!");
        return resultVal;
    }

}




























