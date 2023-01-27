package com.test;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import com.test.shop.repository.BasketRepository;
import com.test.shop.repository.JdbcBasketRepository;
import com.test.shop.repository.JdbcMemberRepository;
import com.test.shop.repository.JdbcProductRepository;
import com.test.shop.repository.MemberRepository;
import com.test.shop.repository.ProductRepository;
import com.test.shop.service.BasketService;
import com.test.shop.service.MemberService;
import com.test.shop.service.ProductService;

@Configuration
public class SpringConfig {

	private final DataSource dataSource;
	
	public SpringConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	
	@Bean
	public MemberRepository memberRepository() {
		return new JdbcMemberRepository(dataSource);
	}
	
	@Bean
	public ProductService productService() {
		return new ProductService(productRepository());
	}
	
	@Bean
	public ProductRepository productRepository() {
		return new JdbcProductRepository(dataSource);
	}
	
	@Bean
	public BasketService basketService() {
		return new BasketService(basketRepository(), memberRepository());
	}
	
	@Bean
	public BasketRepository basketRepository() {
		return new JdbcBasketRepository(dataSource);
	}
	
	@Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
		return new HiddenHttpMethodFilter();
	}
}

