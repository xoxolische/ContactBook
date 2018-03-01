package com.test.pl.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.test.pl.dao.UserDao;
import com.test.pl.model.UserAuth;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		UserAuth userAuth = new UserAuth(userDao.getByEmail(login));
		if (userAuth.getUsername() == null || userAuth.getPassword() == null) {
			throw new UsernameNotFoundException("No user with login " + login);
		}
		return userAuth;
	}

}
