package com.lcwak.userService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.lcwak.model.User;

@Service
public class UserService {

	private List<User> store=new ArrayList<>();
	
	
	public UserService()
	{
		store.add(new User(UUID.randomUUID().toString(),"AKASH ROUT","akash@gmail.com"));
		store.add(new User(UUID.randomUUID().toString(),"RASHMI nayak","rashmi@gmail.com"));
		store.add(new User(UUID.randomUUID().toString(),"RAJA samal","raja@gmail.com"));
		store.add(new User(UUID.randomUUID().toString(),"LIPU BENTA","lipu@gmail.com"));
	}
	
	public List<User> getUsers()
	{
		return this.store;
	}
}
