package com.test.shop.service;

import com.test.shop.domain.Basket;
import com.test.shop.repository.BasketRepository;

public class SetDelete implements SetBasket {
	private final BasketRepository basketRepository;
	
	public SetDelete(BasketRepository basketRepository) {
		this.basketRepository = basketRepository;
	}
	
	@Override
	public int updateBasket(Basket basket) {
		return basketRepository.deleteProductInBasket(basket);
	}
}