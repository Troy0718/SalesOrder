package main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import main.model.Products;
import main.service.ProductsService;

@Controller
public class ProductsController {

	@Autowired
	private ProductsService productsService;

	@GetMapping("/addProducts")
	public String showForm(Model mdoel) {
		mdoel.addAttribute("products", new Products());
		return "products-form";
	}

	@PostMapping("/processProductsForm")
	public String showProductData(@ModelAttribute Products products) {
		productsService.saveOrUpdate(products);
		return "redirect:/showProducts";
	}

	@GetMapping("/showProducts")
	public String getProducts(Model model) {
		List<Products> products = productsService.getAll();
		model.addAttribute("products", products);
		return "products";
	}

	@GetMapping("/deleteProducts/{prodNum}")
	public String deleteProducts(@PathVariable long prodNum, Model model) {
		Products products = productsService.getById(prodNum);
		if (products != null) {
			productsService.delete(prodNum);
		}
		return "redirect:/showProducts";
	}

	@GetMapping("/editProducts/{prodNum}")
	public String editProducts(@PathVariable long prodNum, Model model) {
		Products products = productsService.getById(prodNum);
		if (products != null) {
			model.addAttribute("products", products);
			return "products-form";
		}
		return "redirect:/showProducts";
	}

}
