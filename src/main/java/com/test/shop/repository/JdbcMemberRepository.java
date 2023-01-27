package com.test.shop.repository;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.test.shop.domain.Member;

@Repository
public class JdbcMemberRepository implements MemberRepository {
	
	private final JdbcTemplate jdbcTemplate;
	private String id;
	
	public JdbcMemberRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		this.id = "";
	}
	
	private void setId(String id) {
		this.id = id;
	}
	
	@Override
	public Optional<Member> logIn(String id, String pw){
		setId(id);
		List<Member> result = jdbcTemplate.query("select * from member where memberId = ? and pw = ?", memberRowMapper(), id, pw); 
		return result.stream().findAny();
	}
	
	@Override
	public String getLoginId() {
		return id;
	}
	
	@Override
	public Member memberSave(Member member) {
		String sql = "insert into member (memberId, pw) values(?,?)";
		jdbcTemplate.update(sql, new Object[] {member.getMemberId(),member.getPw()});
		return member;
	}
	
	@Override
	public Optional<Member> findById(String id) {
		List<Member> result = jdbcTemplate.query("select * from member where memberId = ?", memberRowMapper(), id);
				 return result.stream().findAny();
	}

	private RowMapper<Member> memberRowMapper() { //객체 자체를 반환
		return (rs, rowNum) -> {
		Member member = new Member();
		member.setMemberId(rs.getString("memberId"));
		member.setPw(rs.getString("pw"));
		return member;
		};
	}
	
}