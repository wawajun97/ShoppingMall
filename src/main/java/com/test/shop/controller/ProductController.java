package com.test.shop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.shop.domain.Product;
import com.test.shop.service.ProductService;


@Controller
public class ProductController {
	private final ProductService productservice;
	
	public ProductController(ProductService productservice) {
		this.productservice = productservice;
	}
	
	@GetMapping("searchProduct") 
	public String searchProduct() {
		return "searchProduct";
	}
	
	@PostMapping("searchProduct")
	public String displayProduct(@RequestParam("checkInfo") String radioButton, @RequestParam("search") String search, Model model){
		List<Product> product = productservice.selectRadioButton(radioButton, search);
		model.addAttribute("productList", product);
		return "displayProduct";
	}
}
