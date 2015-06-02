package com.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.demo.model.User;

public interface UserDao extends CrudRepository<User, Long>{

}
