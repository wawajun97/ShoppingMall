package com.test.shop.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.test.shop.domain.Basket;

public class JdbcBasketRepository implements BasketRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	public JdbcBasketRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public int insertBasket(Basket basket) {
		String sql = "insert into basket (memberId, productName) values(?,?)";
		return jdbcTemplate.update(sql, new Object[] {basket.getMemberId(),basket.getProductName()});
	}

	@Override
	public List<Basket> findBasket(String id) {
		List<Basket> result = jdbcTemplate.query("select * from basket where memberId = ?", BasketRowMapper(), id);
		return result;
	}
	
	@Override
	public int deleteProductInBasket(String id, String productName) { 
		return jdbcTemplate.update("delete from basket where memberId = ? and productName = ?",  id, productName);
	}
	
	private RowMapper<Basket> BasketRowMapper() {
		return (rs, rowNum) -> {
		Basket basket = new Basket();
		basket.setMemberId(rs.getString("memberId"));
		basket.setProductName(rs.getString("productName"));
		
		return basket;
		};
	}
}
