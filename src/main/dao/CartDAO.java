package main.dao;

import java.util.List;

import main.model.Cart;
import main.model.CartDetail;

public interface CartDAO {

	public List<CartDetail> getAllDetail();

	public Cart getByIdWithCartDetail(long cartNum);

	public Cart getCartByUserId(String userId);

	public List<Cart> getAll();

	public Cart getById(long cartNum);

	public void saveOrUpdate(Cart cart);

	public void delete(long cartNum);
}
