package com.test.shop.repository;

import java.util.List;

import com.test.shop.domain.Basket;

public interface BasketRepository {
	int insertBasket(Basket basket);
	List<Basket> findBasket(String id);
	int deleteProductInBasket(Basket basket);
	Integer findPrice(String productName);
}
