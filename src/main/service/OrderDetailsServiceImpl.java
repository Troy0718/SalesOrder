package main.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.dao.OrderDetailsDAO;
import main.model.OrderDetails;

@Service
@Transactional
public class OrderDetailsServiceImpl implements OrderDetailsService {

	@Autowired
	OrderDetailsDAO orderDetailsDAO;

	@Override
	public OrderDetails getById(int id) {

		return orderDetailsDAO.getById(id);
	}

	@Override
	public void saveOrUpdate(OrderDetails orderDetails) {
		orderDetailsDAO.saveOrUpdate(orderDetails);
	}

	@Override
	public void delete(int id) {
		orderDetailsDAO.delete(id);

	}

	@Override
	public int checkOrderdetails(long prodNum, List<OrderDetails> orderDetails) {
		int checknum = 0;
		for (OrderDetails item : orderDetails) {

			if (item.getProdNum() == prodNum) {
				checknum = item.getId();
				return checknum;
			}

		}
		return checknum;
	}

}
