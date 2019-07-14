package com.ssm.system.user.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.system.api.user.UserMapper;
import com.ssm.system.user.model.User;
import com.ssm.system.user.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public int select(Map<String, String> map) {
		// TODO Auto-generated method stub
		return userMapper.select(map);
	}

	@Override
	public int insertSelective(User user) {
		// TODO Auto-generated method stub
		return userMapper.insertSelective(user);
	}
		
}
