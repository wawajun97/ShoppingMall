package com.test.shop.service;

import java.util.List;

import com.test.shop.domain.Basket;
import com.test.shop.repository.BasketRepository;
import com.test.shop.repository.MemberRepository;

public class BasketService {
	private final BasketRepository basketRepository;
	private final MemberRepository memberRepository;
	
	public BasketService(BasketRepository basketRepository, MemberRepository memberRepository) {
		this.basketRepository = basketRepository;
		this.memberRepository = memberRepository;
	}
	
	public List<Basket> findMyBasket(){
		String id = memberRepository.getLoginId();
		return basketRepository.findBasket(id);
	}
	
	public Basket insertBasket(List<String> productName) {
		return UpdateBasket(new SetInsert(basketRepository), productName);
	}
	
	public Basket deleteProductInBasket(List<String> productName) {
		return UpdateBasket(new SetDelete(basketRepository), productName);
	}
	
	private Basket UpdateBasket(SetBasket setBasket, List<String> productName) {
		Basket basket = new Basket();
		for(int i = 0;i<productName.size();i++) {
			basket.setProductName(productName.get(i));
			basket.setMemberId(memberRepository.getLoginId());
			basket.setPrice(basketRepository.findPrice(productName.get(i)));
			setBasket.updateBasket(basket);
		}
		return basket;
	}
}