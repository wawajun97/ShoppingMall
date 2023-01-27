package com.test.shop.domain;

public class Product {
	private String productId;
	private String productName;
	private int price;
	private String category;
	
	public String getProductId() {
		return productId;
	}
	
	public void setProductId(String id) {
		this.productId = id;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String name) {
		this.productName = name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
}