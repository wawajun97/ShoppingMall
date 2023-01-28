package com.test.shop.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.test.shop.domain.Product;

@Repository
public class JdbcProductRepository implements ProductRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	public JdbcProductRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Product> searchProductByName(String productName) { //상품명 입력 안하고 검색하면 에러나서 list
		List<Product> result = jdbcTemplate.query("select * from product where productname = ?", ProductRowMapper(), productName);
		 return result;
	}
	
	@Override
	public List<Product> searchProductByCategory(String category) {
		List<Product> result = jdbcTemplate.query("select * from product where category = ?", ProductRowMapper(), category);
		 return result;
	}
	
	@Override
	public List<Product> searchAll() {
		List<Product> result = jdbcTemplate.query("select * from product", ProductRowMapper());
		 return result;
	}
	
	private RowMapper<Product> ProductRowMapper() {
		return (rs, rowNum) -> {
		Product product = new Product();
		product.setProductId(rs.getString("productId"));
		product.setProductName(rs.getString("productName"));
		product.setPrice(rs.getInt("price"));
		product.setCategory(rs.getString("category"));
		return product;
		};
	}
	
}
