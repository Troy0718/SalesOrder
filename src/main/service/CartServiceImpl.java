package main.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.dao.CartDAO;
import main.model.Cart;
import main.model.CartDetail;

@Service
@Transactional
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDAO CartDao;

	/**
	 * 回傳所有Cart
	 */
	@Override
	public List<Cart> getAll() {
		return CartDao.getAll();
	}

	/**
	 * 透過id取出指定購物車
	 */
	@Override
	public Cart getById(long cardNum) {
		return CartDao.getById(cardNum);
	}

	/**
	 * 更新、新增購物車
	 */
	@Override
	public void saveOrUpdate(Cart Cart) {
		CartDao.saveOrUpdate(Cart);
	}

	/**
	 * 透過id 刪除指定購物車
	 */
	@Override
	public void delete(long cardNum) {
		CartDao.delete(cardNum);
	}

	/**
	 * 傳入Cart CartDetails 為空時新增一筆空的CartDetail
	 */
	@Override
	public void addCartDetailsIfNotExists(Cart Cart) {
		if (Cart.getCartDetails().isEmpty()) {
			Cart.setCartDetails(new ArrayList<CartDetail>());
			saveOrUpdate(Cart);
		}
	}

	/**
	 * 傳入cartNum 取得cart內所有CartDetail
	 */
	@Override
	public Cart getByIdWithCartDetail(long cartNum) {
		return CartDao.getByIdWithCartDetail(cartNum);
	}

	/**
	 * 取出所有CartDetail
	 */
	@Override
	public List<CartDetail> getAllDetail() {
		return CartDao.getAllDetail();
	}

	/**
	 * 藉由userId取出cart
	 */
	@Override
	public Cart getCartByUserId(String userId) {
		return CartDao.getCartByUserId(userId);
	}

	/**
	 * 新增購物車
	 */
	public void addCart(String UserId) {
		Cart cart = new Cart();
		cart.setCartDate(new Date());
		cart.setUserId(UserId);
		CartDao.saveOrUpdate(cart);
	}

	/**
	 * 計算購物車裡的amount
	 */
	@Override
	public void getAmount(String userId) {
		BigDecimal amount = BigDecimal.ZERO;

		Cart cart = CartDao.getCartByUserId(userId);
		List<CartDetail> CartDetails = cart.getCartDetails();
		for (CartDetail item : CartDetails) {
			amount = amount.add(item.getCartPrice().multiply(BigDecimal.valueOf((long) (item.getOrdQty()))));
		}
		cart.setAmount(amount);
		CartDao.saveOrUpdate(cart);
	}

}
