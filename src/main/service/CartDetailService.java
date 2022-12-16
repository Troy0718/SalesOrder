package main.service;

import main.model.CartDetail;

public interface CartDetailService {

	public CartDetail getById(int id);

	public void saveOrUpdate(CartDetail tourDetails);

	public void delete(int id);

	public int checkCartDetail(long prodNum);

	public void addCartDetail(long prodNum, String UserId);

}
