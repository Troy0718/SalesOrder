package main.dao;

import main.model.OrderDetails;
import main.model.Orders;

public interface OrderDetailsDAO {

	public OrderDetails getById(int id);

	public void saveOrUpdate(OrderDetails orderDetails);

	public void delete(int id);

	public Orders getOrdersByUserId(String userId);
}
