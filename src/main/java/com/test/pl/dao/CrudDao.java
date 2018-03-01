package com.test.pl.dao;

import java.util.List;

public interface CrudDao<E> {

	void create(E entity);
	
	void update(E entity);
	
	E get(long id);
	
	void delete(E entity);
	
	void delete(long id);
	
	List<E> getAll();
}
