package com.test.shop.repository;

import java.util.List;

import com.test.shop.domain.Product;

public interface ProductRepository {
	public List<Product> searchProductByName(String product);
	public List<Product> searchProductByCategory(String Category);
	public List<Product> searchAll();
}
