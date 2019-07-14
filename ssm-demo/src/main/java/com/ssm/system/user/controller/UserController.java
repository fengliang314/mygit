package com.ssm.system.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.system.user.model.User;
import com.ssm.system.user.service.UserService;
import com.ssm.system.util.MD5Util;
import com.ssm.system.util.Msg;

@Controller
@RequestMapping("/system/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/addUser")
	public Object addUser(@RequestParam(value="userName") String userName,
			@RequestParam(value="password") String password,
			@RequestParam(value="email") String email,
			@RequestParam(value="age") Integer age){
		
		User user = new User();
		if (age == null) {
			user.setAge(18);
		}
		if (email == null) {
			user.setEmail("123456@163.com");
		}
		user.setAge(age);
		user.setUserName(userName);
		user.setPassword(MD5Util.digest(password));
		user.setEmail(email);
		userService.insertSelective(user);
		
		
		return Msg.success();
	}
	
	@ResponseBody
	@RequestMapping("/login")
    public Object select(String userName,String password/*,String verifyCode,HttpSession session*/) {
		/*Map<String, Object> resultMap = new HashMap<String, Object>();
		String session_verifyCode = (String) session.getAttribute("verifyCode");
		if (verifyCode == null || !verifyCode.equals(session_verifyCode)) {
			resultMap.put("success", false);
			resultMap.put("error", "验证码不正确,请重新输入");
		}
		*/
		Map<String,String> map = new HashMap<>();
		map.put("userName", userName);
		map.put("password", MD5Util.digest(password));
		
        int count = userService.select(map);
        
        if (count>0) {
        	return Msg.success().add("message","登陆成功");
		}else {
			return Msg.fail().add("message","用户名或密码错误");
		}
        
    }
	
}
