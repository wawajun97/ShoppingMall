package com.test.shop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.shop.domain.Basket;
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
	public String insertBasket(@RequestParam(value = "basket", defaultValue = "") List<String> productName) {
		basketservice.insertBasket(productName); //장바구니에 추가
		return "redirect:searchProduct";
	}
	
	@GetMapping("myBasket")
	public String myBasket(Model model) {
		List<Basket> product = basketservice.findMyBasket();
		model.addAttribute("productList",product); //장바구니 보기
		return "myBasket";
	}
	
	@PostMapping("myBasket")
	public String deleteBasket(@RequestParam(value = "basket", defaultValue = "") List<String> productName) {
		basketservice.deleteProductInBasket(productName); //장바구니에서 삭제
		return "redirect:searchProduct";
	}
}
