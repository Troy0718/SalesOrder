package main.service;

import java.util.List;

import main.model.OrderDetails;

public interface OrderDetailsService {

	public OrderDetails getById(int id);

	public void saveOrUpdate(OrderDetails orderDetails);

	public void delete(int id);

	public int checkOrderdetails(long prodNum, List<OrderDetails> orderDetails);
}
