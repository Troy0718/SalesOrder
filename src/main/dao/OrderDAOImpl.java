package main.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import main.model.Orders;

@Repository
@Transactional
public class OrderDAOImpl implements OrdersDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Orders> getAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Orders t", Orders.class).list();
	}

	@Override
	public Orders getById(long ordNum) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Orders.class, ordNum);
	}

	@Override
	public void saveOrUpdate(Orders orders) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(orders);
	}

	@Override
	public void delete(long ordNum) {
		Session session = sessionFactory.getCurrentSession();
		Orders orders = getById(ordNum);
		session.delete(orders);
	}

	@Override
	public Orders getByIdWithOrderDetails(long ordNum) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Orders t left join fetch t.orderDetails where t.id = :id", Orders.class)
				.setParameter("ordNum", ordNum).getSingleResult();
	}//id 改 ordNum

	@Override
	public Orders getOrdersByUserId(String userId) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Orders t  where userId = :userId", Orders.class).setParameter("userId", userId)
				.getSingleResult();
	}

}
