package main.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import main.model.Cart;
import main.model.CartDetail;
import main.model.OrderDetails;
import main.model.Orders;
import main.service.CartService;
import main.service.OrderDetailsService;
import main.service.OrdersService;

@Controller
public class OrdersController {

	@Autowired
	private OrdersService ordersService;

	@Autowired
	private OrderDetailsService orderDetailsService;

	@Autowired
	private CartService cartService;

	// 展示order-confirm
	@GetMapping("/processConfirm")
	public String showCofirm(Model model, Principal principal) {
		Cart cart = cartService.getCartByUserId(principal.getName());
		long num = ordersService.checkUserid(principal.getName());

		if (num == 0) {
			Orders orders = new Orders();
			// 建立orders 將 cart 資料塞入order 並寫物資料庫
			orders.setOrdDate(new Date());
			orders.setAmount(cart.getAmount());
			orders.setUserId(cart.getUserId());
			// orders.setPayment(cart.getPayment()); cart enum 無法塞入 cart enum
			ordersService.saveOrUpdate(orders);

			// 建立OrderDetails 將 orderDetails 資料塞入order 並寫物資料庫
			for (CartDetail item : cart.getCartDetails()) {
				OrderDetails orderDetails = new OrderDetails();
				orderDetails.setDiscount(item.getDiscount());
				orderDetails.setOrdPrice(item.getCartPrice());
				orderDetails.setOrdQty(item.getOrdQty());
				orderDetails.setProdNum(item.getProdNum());
				orderDetails.setOrders(orders);
				orderDetailsService.saveOrUpdate(orderDetails);
			}

			List<OrderDetails> orderDetailList = orders.getOrderDetails();
			model.addAttribute("orders", orders);
			model.addAttribute("orderDetailList", orderDetailList);
			return "order-confirm";
		}

		Orders orders = ordersService.getById(num);
		List<OrderDetails> orderDetails = orders.getOrderDetails();
		for (CartDetail item : cart.getCartDetails()) {
			int checknum = orderDetailsService.checkOrderdetails(item.getProdNum(), orderDetails);
			OrderDetails orderDetails1 = orderDetailsService.getById(checknum);
			orderDetails1.setOrdQty(orderDetails1.getOrdQty() + item.getOrdQty());
			orderDetailsService.saveOrUpdate(orderDetails1);
			orderDetails1 = null;
		}
		ordersService.getAmount(principal.getName());
		List<OrderDetails> orderDetailList = orders.getOrderDetails();
		model.addAttribute("orders", orders);
		model.addAttribute("orderDetailList", orderDetailList);
		return "order-confirm";
	}

	// 交易完成 finish
	@GetMapping("/orderFinish")
	public String goFinish(Model model, Principal principal) {
		Orders orders = ordersService.getOrdersByUserId(principal.getName());
		model.addAttribute("orders", orders);		
		return "order-finish";
	}

}
