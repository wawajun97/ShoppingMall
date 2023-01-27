package com.test.shop.service;

import java.util.List;

import com.test.shop.domain.Product;
import com.test.shop.repository.ProductRepository;

public class ProductService {

	private final ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	//레퍼지토리에서 가져온 함수를 바로 return해주는데 이게 의미가 있나?? => service를 안거치고 controller에서 바로 사용하면 안되나?
	public List<Product> searchOne(String name) { 
		return productRepository.searchProductByName(name);
	}
	
	public List<Product> searchCategory(String category) {
		return productRepository.searchProductByCategory(category);
	}
	
	public List<Product> searchAll() {
		return productRepository.searchAll();
	}
}
