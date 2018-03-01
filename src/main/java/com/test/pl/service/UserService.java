package com.test.pl.service;

import com.test.pl.model.User;

public interface UserService extends CrudInterface<User> {
	
	User getByEmail(String email);
}
