package com.demo.service;

import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.UserDao;
import com.demo.model.User;

@Service
public class UserService {
	
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	ConcurrentHashMap<Long, User> userMap = new ConcurrentHashMap<Long, User>();
	
	@Autowired
	UserDao userDao;
	
	public User getUserById(Long id){
		User user = userMap.get(id);
		if(user == null)
		{
			log.debug("User not found in ConcurrentHashMap");
			user = userDao.findOne(id);
			if(user !=null){
				userMap.put(id, user);
			}
		}else{
		log.debug("User found in ConcurrentHashMap");
		}
		return user;
	}
	
	public void flushUserMap(){
			userMap.clear();
	}
	
	public void saveUser(User user){
		userDao.save(user);
	}
	
	

}
