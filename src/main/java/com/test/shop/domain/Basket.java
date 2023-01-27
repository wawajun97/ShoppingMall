package com.test.shop.domain;

public class Basket {
	private String memberId;
	private String productName;
	
	public String getMemberId() {
		return memberId;
	}
	
	public void setMemberId(String id) {
		this.memberId = id;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String name) {
		this.productName = name;
	}
}