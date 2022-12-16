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
import main.service.CartDetailService;
import main.service.CartService;
import main.service.ProductsService;

@Controller
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private CartDetailService cartDetailService;

	@Autowired
	private ProductsService productsService;

	@GetMapping("/showCart")
	public String getCart(Model model, Principal principal) {
		Cart cart = cartService.getCartByUserId(principal.getName());
		model.addAttribute("cart", cart);
		List<CartDetail> cartDetails = cart.getCartDetails();
		model.addAttribute("cartDetails", cartDetails);
		return "cart";
	}

	@GetMapping("/clearCart/{cartNum}")
	public String clearCartDetailOfCart(@PathVariable long cartNum, Model model) {
		Cart cart = cartService.getById(cartNum);
		List<CartDetail> cartDetails = cart.getCartDetails();
		cartDetails.removeAll(cartDetails);
		System.out.println(cartDetails.isEmpty());

		cartService.saveOrUpdate(cart);
		for(CartDetail item : cartDetails ) {
			cartDetailService.delete(item.getId());
		}

		return "redirect:/showCart";
	}

}
