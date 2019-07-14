package com.ssm.test;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssm.system.api.user.UserMapper;
import com.ssm.system.user.model.User;
import com.ssm.system.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class Test01 extends AbstractTransactionalJUnit4SpringContextTests{
	
	
	@Autowired  
    private UserService userService;  
	
	@Resource
	private RedisTemplate redisTemplate;
	
	private static final Logger log = Logger.getLogger(Test01.class.getName());
	
	@Test
	public void testredis(){
		redisTemplate.opsForValue().set("username", "航天科工");
		System.out.println("xxxxxxxxx");
		log.info("value:" + redisTemplate.opsForValue().get("username"));
	}
	
	@Test
	public void test(){
		User user = new User();
		user.setAge(18);
		user.setEmail("12345621163.com");
		user.setUserName("aaccc");
		user.setPassword("123456");
		int i = userService.insertSelective(user);
		System.out.println(userService);
		System.out.println("success....");
	}
	
/*	
	@Test
	public void update(){
		User user = new User();
		user.setUserId(1l);
		user.setAge(10);
		user.setEmail("123456@163.com");
		userService.updateByPrimaryKey(user);
		System.out.println("0.0.0.0.0.0");
	}
	*/
	@Test
	public void select(){
		Map<String,String> map = new HashMap<>();
		map.put("userName", "admin");
		map.put("password", "111111");
        int count = userService.select(map);
		System.out.println(count);
		
	}
	
}
