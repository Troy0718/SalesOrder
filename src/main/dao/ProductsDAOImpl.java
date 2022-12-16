package main.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import main.model.Products;

@Repository
@Transactional
public class ProductsDAOImpl implements ProductsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Products> getAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Products t", Products.class).list();
	}

	@Override
	public Products getById(long prodNum) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Products.class, prodNum);
	}

	@Override
	public void saveOrUpdate(Products products) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(products);
	}

	@Override
	public void delete(long prodNum) {
		Session session = sessionFactory.getCurrentSession();
		Products products = getById(prodNum);
		session.delete(products);
	}

}
