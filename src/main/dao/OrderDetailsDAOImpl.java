package main.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import main.model.OrderDetails;
import main.model.Orders;

@Repository
@Transactional
public class OrderDetailsDAOImpl implements OrderDetailsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public OrderDetails getById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(OrderDetails.class, id);
	}

	@Override
	public void saveOrUpdate(OrderDetails orderDetails) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(orderDetails);
	}

	@Override
	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		OrderDetails orderDetails = getById(id);
		session.delete(orderDetails);
	}

	@Override
	public Orders getOrdersByUserId(String userId) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Orders t  where userId = :userId", Orders.class).setParameter("userId", userId)
				.getSingleResult();
	}

}
