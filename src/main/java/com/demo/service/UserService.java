package com.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.User;

@Service
public class UserService {
	
//	@Autowired
	
	public List<User> getAll(){
		return new ArrayList<User>();
	}

}
