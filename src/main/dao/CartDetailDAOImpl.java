package main.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import main.model.CartDetail;

@Repository
@Transactional
public class CartDetailDAOImpl implements CartDetailDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public CartDetail getById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(CartDetail.class, id);
	}

	@Override
	public void saveOrUpdate(CartDetail cartDetail) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(cartDetail);
	}

//	@Override
//	public void delete(int id) {
//		Session session = sessionFactory.getCurrentSession();
//		CartDetail cartDetail = getById(id);
//		session.delete(cartDetail);
//	}
	
	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		session.createQuery("delete from CartDetail where id= :id").setParameter(id, session).executeUpdate();
	}
	
	

	@Override
	public List<CartDetail> getAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from CartDetail t", CartDetail.class).list();
	}

}
