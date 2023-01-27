package com.test.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.shop.domain.Product;

@Controller
public class ProductSubmainController {
	@PostMapping("product/{name}")
	public String bed(@PathVariable("name") String name, @RequestParam("product") Product product) {
		name = product.getProductId();
		return "product/" + name;
	}
	
	@GetMapping("product/{name}")
	public String chair() {
		return "product/chair";
	}
//	
//	@GetMapping("product/fish")
//	public String fish() {
//		return "product/fish";
//	}
//	
//	@GetMapping("product/meat")
//	public String meat() {
//		return "product/meat";
//	}
//	
//	@GetMapping("product/refrigerator")
//	public String refrigetator() {
//		return "product/refrigerator";
//	}
//	
//	@GetMapping("product/table")
//	public String table() {
//		return "product/table";
//	}
//	
//	@GetMapping("product/tv")
//	public String tv() {
//		return "product/tv";
//	}
//	
//	@GetMapping("product/washer")
//	public String washer() {
//		return "product/washer";
//	}
//	
//	@GetMapping("product/water")
//	public String water() {
//		return "product/water";
//	}
}
