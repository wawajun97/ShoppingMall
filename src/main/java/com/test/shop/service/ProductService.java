package com.test.shop.service;

import java.util.List;

import com.test.shop.domain.Product;
import com.test.shop.repository.ProductRepository;

public class ProductService {

	private final ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public List<Product> searchOne(String name) { 
		return productRepository.searchProductByName(name);
	}
	
	public List<Product> searchCategory(String category) {
		return productRepository.searchProductByCategory(category);
	}
	
	public List<Product> searchAll() {
		return productRepository.searchAll();
	}
	
	public List<Product> selectRadioButton(String radioButton,String search) {
		List<Product> product;
		if(radioButton.equals("물건")) { 
			product = searchOne(search);
		}
		else if(radioButton.equals("카테고리")){
			product = searchCategory(search);
		}
		else {
			product = searchAll();
		}
		return product;
	}
}
