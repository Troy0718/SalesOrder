package main.dao;

import java.util.List;

import main.model.CartDetail;

public interface CartDetailDAO {

	public List<CartDetail> getAll();

	public CartDetail getById(int id);

	public void saveOrUpdate(CartDetail cartDetail);

	public void delete(int id);
}
