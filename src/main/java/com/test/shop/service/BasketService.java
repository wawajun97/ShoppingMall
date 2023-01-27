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
	
	public Basket insertBasket(List<String> productName,Basket basket) {
		for(int i = 0;i<productName.size();i++) {
			basket.setProductName(productName.get(i));
			basket.setMemberId(memberRepository.getLoginId());
			basketRepository.insertBasket(basket);
		}
		return basket;
	}
	
	public List<Basket> findMyBasket(){
		String id = memberRepository.getLoginId();
		return basketRepository.findBasket(id);
	}
	
	public void deleteProductInBasket(List<String> productName) {
		for(int i = 0;i<productName.size();i++) {
			String id = memberRepository.getLoginId();
			String name = productName.get(i);
			basketRepository.deleteProductInBasket(id, name);
		}
	}
}
