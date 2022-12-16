package main.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import main.model.Cart;
import main.model.CartDetail;
import main.model.Products;
import main.service.CartDetailService;
import main.service.CartService;
import main.service.ProductsService;

@Controller
public class CartDetailController {

	@Autowired
	ProductsService productsService;

	@Autowired
	CartService cartService;

	@Autowired
	CartDetailService cartDetailService;

	// 加入購物車功能
	@GetMapping("/putCartDetail/{prodNum}")
	public String putCart(@PathVariable long prodNum, Model model, Principal principal) {

		String userId = principal.getName();
		// 判斷目前是否有購物車，沒有，則新增一輛購物車
		if (cartService.getAll().isEmpty()) {
			cartService.addCart(userId);
		}
		// 判斷是否有購物車 有
		if (cartService.getCartByUserId(userId) != null) {
			// 判斷目前是否有購物車消費項目，沒有則新增一項購物車消費項目
			if (cartService.getAllDetail().isEmpty()) {
				cartDetailService.addCartDetail(prodNum, userId);
			}
			// 判斷目前是否有購物車消費項目，有，則檢查購物車消費項目
			if (cartService.getAllDetail().isEmpty() != true) {
				boolean addfinish = false;
				// 檢查購物車消費項目是否重覆
				int num = cartDetailService.checkCartDetail(prodNum);
				if (num != 0) {
					CartDetail cartDetail = cartDetailService.getById(num);
					cartDetail.setOrdQty(cartDetail.getOrdQty() + 1);
					addfinish = true;
					cartDetailService.saveOrUpdate(cartDetail);
				}
				// 購物車消費項目無重複
				if (addfinish == false) {
					cartDetailService.addCartDetail(prodNum, userId);

				}
			}

		}
		//每次放入購物車商品都重新計算總金額
		cartService.getAmount(principal.getName());
		// 準備重新進入首頁 先將資料庫所有商品項目先存入model
		List<Products> products = productsService.getAll();
		model.addAttribute("products", products);
		return "index";
	}

	// 刪除購物車消費項目
	@GetMapping("/deleteCartDetail/{id}")
	public String deleteCartDetail(@PathVariable int id, Principal principal) {

//		Cart cart = cartService.getCartByUserId(principal.getName());
//		List<CartDetail> cartDetails = cart.getCartDetails();
//		// 使用foreach cartDetails 找到該筆id
//		CartDetail cartDetail = cartDetails.stream().filter(x -> x.getId() == id).findFirst().get();
//		if (cartDetail != null) {
//			cartDetails.remove(cartDetail);
//			// cartDetailService.delete(id);
//			cartService.saveOrUpdate(cart);
//		}
		cartDetailService.delete(id);
		return "redirect:/showCart";
	}

	// 更新購物車消費數量項目
	@GetMapping("/updateCartDetail/{id}")
	public String updateCartDetail(@PathVariable int id, Model model, Principal principal) {
		
		CartDetail cartDetail = cartDetailService.getById(id);
		if (cartDetail != null) {
			Cart cart = cartService.getCartByUserId(principal.getName());
			List<CartDetail> cartDetails = cart.getCartDetails();
			cartService.getAmount(principal.getName());
			model.addAttribute("cart", cart);
			model.addAttribute("cartDetails", cartDetails);
			return "cart";
		}
		return "redirect:/showCart";
	}

}
