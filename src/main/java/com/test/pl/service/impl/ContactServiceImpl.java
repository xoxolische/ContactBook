package com.test.pl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.pl.dao.ContactDao;
import com.test.pl.model.Contact;
import com.test.pl.service.ContactService;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactDao contactDao;

	@Override
	public void create(Contact entity) {
		contactDao.create(entity);
	}

	@Override
	public void update(Contact entity) {
		contactDao.update(entity);
	}

	@Override
	public Contact get(long id) {
		return contactDao.get(id);
	}

	@Override
	public List<Contact> getAll() {
		return contactDao.getAll();
	}

	@Override
	public void delete(long id) {
		contactDao.delete(id);
	}

	@Override
	public void delete(Contact entity) {
		contactDao.delete(entity);
	}

	@Override
	public List<Contact> getMore(long lastDate, int limit) {
		return contactDao.getMore(lastDate, limit);
	}

	@Override
	public Contact getByEmail(String email) {
		return contactDao.getByEmail(email);
	}
}
