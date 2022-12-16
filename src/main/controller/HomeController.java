package main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import main.model.Products;
import main.service.ProductsService;

@Controller
public class HomeController {

	@Autowired
	ProductsService productsService;

	@RequestMapping("/")
	public String getHome(Model model) {
		List<Products> products = productsService.getAll();
		model.addAttribute("products", products);
		return "index";
	}

}
