package main.dao;

import java.util.List;

import main.model.Orders;

public interface OrdersDAO {

	public List<Orders> getAll();

	public Orders getById(long ordNum);

	public void saveOrUpdate(Orders orders);

	public void delete(long ordNum);

	public Orders getByIdWithOrderDetails(long ordNum);

	public Orders getOrdersByUserId(String userId);

}
