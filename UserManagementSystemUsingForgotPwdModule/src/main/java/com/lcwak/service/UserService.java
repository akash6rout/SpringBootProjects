package com.lcwak.service;

import com.lcwak.bean.User;


public interface UserService {
	
	public User saveUser(User user);
	
	public void removeSessionMessage();
	
	//public boolean checkEmail(String email);
	

}
