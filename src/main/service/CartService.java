package main.service;

import java.util.List;

import main.model.Cart;
import main.model.CartDetail;

public interface CartService {
	public List<Cart> getAll();

	public List<CartDetail> getAllDetail();

	public Cart getById(long cartNum);

	public void saveOrUpdate(Cart Cart);

	public void delete(long cartNum);

	public void addCartDetailsIfNotExists(Cart Cart);

	public Cart getByIdWithCartDetail(long cartNum);

	public Cart getCartByUserId(String userId);

	public void getAmount(String userId);

	public void addCart(String UserId);

}
