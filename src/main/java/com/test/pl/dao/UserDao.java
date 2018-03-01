package com.test.pl.dao;

import com.test.pl.model.User;

public interface UserDao extends CrudDao<User> {
	
	User getByEmail(String email);

}
