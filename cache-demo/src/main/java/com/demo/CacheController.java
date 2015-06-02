package com.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.model.User;
import com.demo.service.UserService;

@RestController
@RequestMapping("/cache-demo")
public class CacheController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/sampleRequest", method = GET)
	@ResponseBody
	public User getSampleUser() {
		User user = new User();
		user.setFirstName("EL");
		user.setLastName("TACO GRANDE");
		user.setUserName("elTacoGrande");
		return user;
	}

	@RequestMapping(value = "/user/{userId}", method = GET)
	@ResponseBody
	public User getUser(@PathVariable Long userId) {
		return userService.getUserById(userId);
	}

	@RequestMapping(value = "/user", method = POST)
	@ResponseBody
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		userService.saveUser(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/flush", method = DELETE)
	public void clearCache(){
		userService.flushUserMap();
	}

}
