package main.service;

import java.util.List;

import main.model.Orders;

public interface OrdersService {

	public List<Orders> getAll();

	public Orders getById(long ordNum);

	public void saveOrUpdate(Orders orders);

	public void delete(long ordNum);

	public Orders getByIdWithOrderDetails(long ordNum);

	public Orders getOrdersByUserId(String userId);

	public long checkUserid(String userId);

	public void getAmount(String userId);

}
