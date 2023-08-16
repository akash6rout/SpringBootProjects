package com.lcwak.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwak.bean.User;


public interface UserRepo extends JpaRepository<User, Integer>{

	public User findByEmail(String email);
	
	User findByEmailAndMobileno(String email,String mobilenum);
	
}
