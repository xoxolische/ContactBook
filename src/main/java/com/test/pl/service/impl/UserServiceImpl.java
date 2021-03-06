package com.test.pl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.pl.dao.UserDao;
import com.test.pl.model.User;
import com.test.pl.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public void create(User entity) {
		entity.setPassword(new BCryptPasswordEncoder().encode(entity.getPassword()));
		userDao.create(entity);
	}

	@Override
	public void update(User entity) {
		userDao.update(entity);
	}

	@Override
	public User get(long id) {
		return userDao.get(id);
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

	@Override
	public void delete(long id) {
		userDao.delete(id);
	}

	@Override
	public void delete(User entity) {
		userDao.delete(entity);
	}

	@Override
	public User getByEmail(String email) {
		return userDao.getByEmail(email);
	}
}
