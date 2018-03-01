package com.test.pl.dao;

import java.util.List;

import com.test.pl.model.Contact;

public interface ContactDao extends CrudDao<Contact> {

	List<Contact> getMore(long lastDate, int limit);

	Contact getByEmail(String email);
}
