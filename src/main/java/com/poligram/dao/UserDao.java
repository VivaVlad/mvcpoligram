package com.poligram.dao;

import java.util.List;

import com.poligram.model.User;


public interface UserDao {

	User findById(Long id);

	User findByName(String name);
	
	void save(User user);

	void update(User user);

	void deleteUserById(Long id);

	List<User> findAll();

	void deleteAll();

}

