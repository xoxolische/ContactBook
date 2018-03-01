package com.test.pl.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.test.pl.dao.ContactDao;
import com.test.pl.hibernate.HibernateUtil;
import com.test.pl.model.Contact;

@Repository
public class ContactDaoImpl extends AbstractDaoImpl<Contact, Long> implements ContactDao {

	public ContactDaoImpl() {
		super(Contact.class);
	}

	@Override
	public List<Contact> getMore(long lastDate, int limit) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Contact> query = builder.createQuery(Contact.class);
			Root<Contact> root = query.from(Contact.class);
			query.select(root).where(builder.lessThan(root.get("createdAt"), new Timestamp(lastDate)))
					.orderBy(builder.desc(root.get("createdAt")));
			Query<Contact> q = session.createQuery(query);
			q.setMaxResults(limit);
			List<Contact> l = q.getResultList();
			session.getTransaction().commit();
			return l;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}

	@Override
	public Contact getByEmail(String email) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Contact> query = builder.createQuery(Contact.class);
			Root<Contact> root = query.from(Contact.class);
			query.select(root).where(builder.equal(root.get("email"), email));
			Query<Contact> q = session.createQuery(query);
			Contact c = q.getSingleResult();
			session.getTransaction().commit();
			return c;
		} catch (Exception e) {
			session.getTransaction().rollback();
			return null;
		}
	}

}
