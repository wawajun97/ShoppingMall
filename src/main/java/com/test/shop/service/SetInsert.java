package com.test.shop.service;

import com.test.shop.domain.Basket;
import com.test.shop.repository.BasketRepository;

public class SetInsert implements SetBasket {
	private final BasketRepository basketRepository;
	
	public SetInsert(BasketRepository basketRepository) {
		this.basketRepository = basketRepository;
	}
	
	@Override
	public int updateBasket(Basket basket) {
		return basketRepository.insertBasket(basket);
	}
}
