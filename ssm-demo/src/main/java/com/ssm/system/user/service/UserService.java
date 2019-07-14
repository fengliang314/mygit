package com.ssm.system.user.service;

import java.util.Map;

import com.ssm.system.user.model.User;

public interface UserService {
	
	public int select(Map<String, String> map);
	
	int insertSelective(User user);
}
