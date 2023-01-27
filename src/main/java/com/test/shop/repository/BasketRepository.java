package com.test.shop.repository;

import java.util.List;

import com.test.shop.domain.Basket;

public interface BasketRepository {
	Basket insertBasket(Basket basket);
	List<Basket> findBasket(String id);
	void deleteProductInBasket(String id, String productName);
}
