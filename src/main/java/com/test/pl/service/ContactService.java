package com.test.pl.service;

import java.util.List;

import com.test.pl.model.Contact;

public interface ContactService extends CrudInterface<Contact> {
	
	List<Contact> getMore(long lastDate, int limit);
	
	Contact getByEmail(String email);
}
