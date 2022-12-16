package main.service;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.dao.OrdersDAO;
import main.model.OrderDetails;
import main.model.Orders;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	OrdersDAO ordersDAO;

	@Override
	public List<Orders> getAll() {

		return ordersDAO.getAll();
	}

	@Override
	public Orders getById(long ordNum) {

		return ordersDAO.getById(ordNum);
	}

	@Override
	public void saveOrUpdate(Orders orders) {

		ordersDAO.saveOrUpdate(orders);
	}

	@Override
	public void delete(long ordNum) {

		ordersDAO.delete(ordNum);
	}

	@Override
	public Orders getByIdWithOrderDetails(long ordNum) {

		return ordersDAO.getByIdWithOrderDetails(ordNum);
	}

	@Override
	public Orders getOrdersByUserId(String userId) {

		return ordersDAO.getOrdersByUserId(userId);
	}

	/**
	 * 從orders table 查出該userid 的ordNum
	 */
	@Override
	public long checkUserid(String userId) {
		List<Orders> orders = ordersDAO.getAll();
		long ordNum = 0;
		for (Orders item : orders) {
			if (item.getUserId().equals(userId)) {
				ordNum = item.getOrdNum();
				return ordNum;
			}
		}
		return ordNum;
	}

	/**
	 * 計算購物車裡的amount
	 */
	@Override
	public void getAmount(String userId) {
		BigDecimal amount = BigDecimal.ZERO;

		Orders orders = ordersDAO.getOrdersByUserId(userId);
		List<OrderDetails> orderDetails = orders.getOrderDetails();
		for (OrderDetails item : orderDetails) {
			amount = amount.add(item.getOrdPrice().multiply(BigDecimal.valueOf((long) (item.getOrdQty()))));
		}
		System.out.println(amount);
		orders.setAmount(amount);
		ordersDAO.saveOrUpdate(orders);
	}

}
