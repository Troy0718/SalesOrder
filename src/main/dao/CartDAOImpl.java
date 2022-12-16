package main.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import main.model.Cart;
import main.model.CartDetail;

@Repository
@Transactional
public class CartDAOImpl implements CartDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Cart getById(long cartNum) {		
		Session session = sessionFactory.getCurrentSession();
		return session.get(Cart.class , cartNum);
	}

	@Override
	public void saveOrUpdate(Cart cart) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(cart);		
	}

	@Override
	public void delete(long cartNum) {
		Session session = sessionFactory.getCurrentSession();
		Cart cart = getById(cartNum);
		session.delete(cart);		
	}
	

	@Override
	public List<CartDetail> getAllDetail() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from CartDetail t",CartDetail.class ).list();
	}

	@Override
	public List<Cart> getAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Cart t",Cart.class ).list();
	}

	@Override
	public Cart getByIdWithCartDetail(long cartNum) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Cart t left join fetch t.cart_detail where t.cartNum = :cartNum" , Cart.class).setParameter("cartNum", cartNum).getSingleResult();
	}
	
	@Override
	public Cart getCartByUserId(String userId) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Cart t  where userId = :userId" , Cart.class).setParameter("userId", userId).getSingleResult();
	}
	

}
