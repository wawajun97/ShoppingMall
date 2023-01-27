package com.test.shop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.shop.domain.Basket;
import com.test.shop.domain.Product;
import com.test.shop.service.BasketService;


@Controller
public class BasketController {
private final BasketService basketservice;
	
	public BasketController(BasketService basketservice) {
		this.basketservice = basketservice;
	}
	 
	@GetMapping("displayProduct")
	public String basket() {
		return "displayProduct";
	}
	
	@PostMapping("displayProduct")
	public String insertBasket(@RequestParam("basket") List<String> productName, Basket basket) {
		basketservice.insertBasket(productName, basket);
		return "redirect:searchProduct";
	}
	
	@GetMapping("myBasket")
	public String myBasket(Model model) {
		List<Basket> product = basketservice.findMyBasket();
		model.addAttribute("productList",product);
		return "myBasket";
	}
	
	@PostMapping("myBasket")
	public String deleteBasket(@RequestParam("basket") List<String> productName) {
		basketservice.deleteProductInBasket(productName);
		return "redirect:searchProduct";
	}
}
