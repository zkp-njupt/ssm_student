<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录系统</title>
<link href="../css/login.css" rel="stylesheet" rev="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="../js/jQuery1.7.js"></script>
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="../js/jquery1.42.min.js"></script>
<script type="text/javascript" src="../js/jquery.SuperSlide.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var $tab_li = $('#tab ul li');
	$tab_li.hover(function(){
		$(this).addClass('selected').siblings().removeClass('selected');
		var index = $tab_li.index(this);
		$('div.tab_box > div').eq(login).show().siblings().hide();
	});	
});
</script>
<script type="text/javascript">
$(function(){
/*学生登录信息验证*/
$("#stu_username_hide").focus(function(){
 var username = $(this).val();
 if(username=='输入学号'){
 $(this).val('');
 }
});
$("#stu_username_hide").focusout(function(){
 var username = $(this).val();
 if(username==''){
 $(this).val('输入学号');
 }
});
$("#stu_password_hide").focus(function(){
 var username = $(this).val();
 if(username=='输入密码'){
 $(this).val('');
 }
});
$("#stu_password_hide").focusout(function(){
 var username = $(this).val();
 if(username==''){
 $(this).val('输入密码');
 }
});
$("#stu_code_hide").focus(function(){
 var username = $(this).val();
 if(username=='输入验证码'){
 $(this).val('');
 }
});
$("#stu_code_hide").focusout(function(){
 var username = $(this).val();
 if(username==''){
 $(this).val('输入验证码');
 }
});
$(".stu_login_error").Validform({
	tiptype:function(msg,o,cssctl){
		var objtip=$(".stu_error_box");
		cssctl(objtip,o.type);
		objtip.text(msg);
	},
	ajaxPost:true
});
});
</script>
<script type="text/javascript">
$(function(){
	$(".screenbg ul li").each(function(){
		$(this).css("opacity","0");
	});
	$(".screenbg ul li:first").css("opacity","1");
	var index = 0;
	var t;
	var li = $(".screenbg ul li");	
	var number = li.size();
	function change(index){
		li.css("visibility","visible");
		li.eq(login).siblings().animate({opacity:0},3000);
		li.eq(login).animate({opacity:1},3000);
	}
	function show(){
		login = login + 1;
		if(login <= number-1){
			change(login);
		}else{
			login = 0;
			change(login);
		}
	}
	t = setInterval(show,8000);
	//根据窗口宽度生成图片宽度
	var width = $(window).width();
	$(".screenbg ul img").css("width",width+"px");
});
</script>

  <script>
      $(function () {

          //设置cookie
          function setCookie(){
              var username = $("#stu_username_hide").val();
              var password = $("#stu_password_hide").val();
              var checked = $("input[name='checkbox']").is(':checked');
              if(checked){//判断是否选中了记住密码框
                  //选中了
                  //设置cookie过期时间
                  var date = new Date();
                  date.setTime(date.getTime() + 60*1000);
                  //调用jquery.cookie.js中的方法设置cookie中的用户名
                  $.cookie("login_username",username,{expires:date,path:'/'});
                  //调用jquery.cookie.js中的方法设置cookie中的登陆密码，并使用base64（jquery.base64.js）进行加密
                  $.cookie("login_password",password,{expires:date,path:'/'});
              }else{
                  //没选中
                  $.cookie("login_username", null);
                  $.cookie("login_password", null);
              }
          }
          //清楚所有cookie信息
          function clearAllCookie() {
              var date=new Date();
              date.setTime(date.getTime()-10000);
              var keys=document.cookie.match(/[^ =;]+(?=\=)/g);
              console.log("需要删除的cookie名字："+keys);
              if (keys) {
                  for (var i =  keys.length; i--;)
                      document.cookie=keys[i]+"=0; expire="+date.toGMTString()+"; path=/";
              }
          }
          //获取cookie
          function getCookie(){
              var cookieUserName = $.cookie("login_username");
              var cookiePassword = $.cookie("login_password");

              $("#stu_username_hide").val(cookieUserName);
              //密码存在的话把密码填充到密码文本框
              $("#stu_password_hide").val(cookiePassword);
              $("input[type='checkbox']").attr("checked",true);
          }

          function login(){
              //判断是否选中复选框，如果选中，添加cookie
              var checked = $("input[type='checkbox']").is(":checked");
              if(checked){
                  setCookie();
              }else{
                  clearAllCookie();
              }
          }


          $("#vcodeImg").click(function () {
              this.src="get_cpacha?vl=4&amp;w=160&amp;h=40&amp;t="+new Date().getTime();
          });
          $("#btn").click(function () {

              //判断是否选中复选框，如果选中，添加cookie
              var checked = $("input[type='checkbox']").is(":checked");
              if(checked){
                  setCookie();
              }else{
                  clearAllCookie();
              }



              var data = $("#form").serialize();
              $.ajax({
                  url:"/login/userLogin",
                  data:data,
                  type:"post",
                  dataType:"json",
                  success:function (data) {
                      if("success" == data.type){
                          window.parent.location.href="index";
                      }else{
                          alert(data.msg);
                          $("#vcodeImg").click();//切换验证码
                          $("input[name='checkCode']").val("");//清空验证码输入框
                      }
                  }
              });
          });

      });
  </script>

</head>

<body>
<div id="tab">
  <div class="tab_box"> 
    <!-- 学生登录开始 -->
    <div>
      <div class="stu_error_box"></div>
      <form id="form" class="stu_login_error">
        <div id="username">
          <label>学&nbsp;&nbsp;&nbsp;号：</label>
          <input type="text" id="stu_username_hide" name="username" value="输入学号" nullmsg="学号不能为空！" datatype="s6-18" errormsg="学号范围在6~18个字符之间！" sucmsg="学号验证通过！"/>
          <!--ajaxurl="demo/valid.jsp"--> 
        </div>
        <div id="password">
          <label>密&nbsp;&nbsp;&nbsp;码：</label>
          <input type="password" id="stu_password_hide" name="password" value="输入密码" nullmsg="密码不能为空！" datatype="*6-16" errormsg="密码范围在6~16位之间！" sucmsg="密码验证通过！"/>
        </div>
        <div id="code">
          <label>验证码：</label>
          <input type="text" id="stu_code_hide" name="checkCode"  value="输入验证码" nullmsg="验证码不能为空！" datatype="*4-4" errormsg="验证码有4位数！" sucmsg="验证码验证通过！"/>
          <img src="get_cpacha?vl=4&w=160&h=40" id="vcodeImg" title="点击更换" alt="验证码占位图"/> </div>
        <div id="remember">
          <input type="checkbox" name="remember">
          <label>记住密码</label>
        </div>
        <div id="login">
          <button type="button" id="btn">登录</button>
        </div>
      </form>
    </div>
   <!-- 学生登录结束-->
  </div>
</div>
<div class="bottom">©2014 Leting <a href="javascript:;" target="_blank">关于</a> <span>京ICP证030173号</span>  More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a><img width="13" height="16" src="images/copy_rignt_24.png" /></div>
<div class="screenbg">
  <ul>
    <li><a href="javascript:;"><img src="../images/0.jpg"></a></li>
    <li><a href="javascript:;"><img src="../images/1.jpg"></a></li>
    <li><a href="javascript:;"><img src="../images/2.jpg"></a></li>
  </ul>
</div>
</body>
</html>
