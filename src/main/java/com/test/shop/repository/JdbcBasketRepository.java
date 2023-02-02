package com.test.shop.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.test.shop.domain.Basket;
import com.test.shop.domain.Product;

@Repository
public class JdbcBasketRepository implements BasketRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	public JdbcBasketRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public int insertBasket(Basket basket) {
		String sql = "insert into basket (memberId, productName, price) " +           //장바구니에 있는지 확인하고 없으면 추가
				     "select ?, ?, ? from dual where not exists (select *" +
				" from basket where memberId = ? and productName = ?)";
		return jdbcTemplate.update(sql, new Object[] {basket.getMemberId(),basket.getProductName(), basket.getPrice(),
				                   basket.getMemberId(),basket.getProductName()});
	}

	@Override
	public List<Basket> findBasket(String id) { // 장바구니 보기
		List<Basket> result = jdbcTemplate.query("select * from basket where memberId = ?", BasketRowMapper(), id);
		return result;
	}
	
	@Override
	public int deleteProductInBasket(Basket basket) { // 장바구니에서 삭제
		return jdbcTemplate.update("delete from basket where memberId = ? and productName = ?",  basket.getMemberId(),basket.getProductName());
	}
	
	@Override
	public Integer findPrice(String productName) {
		return jdbcTemplate.queryForObject("select price from product where productName = ?", Integer.class, productName);
	}
	
	@Override
	public Integer getSum(String memberId) {
		return jdbcTemplate.queryForObject("select sum(price) from basket where memberId = ?", Integer.class, memberId);
	}
	
	private RowMapper<Basket> BasketRowMapper() { //ResultSet의 결과를 객체로 변환
		return (rs, rowNum) -> {
		Basket basket = new Basket();
		basket.setMemberId(rs.getString("memberId"));
		basket.setProductName(rs.getString("productName"));
		basket.setPrice(rs.getInt("price"));
		return basket;
		};
	}
}
