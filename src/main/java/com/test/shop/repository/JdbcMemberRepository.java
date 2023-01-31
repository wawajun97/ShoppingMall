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
	public Member logIn(String id, String pw){ //아이디와 비밀번호가 일치하는 데이터 반환
		setId(id);
		Member result = jdbcTemplate.queryForObject("select * from member where memberId = ? and pw = ?", memberRowMapper(), id, pw);
		return result;
	}
	
	@Override
	public String getLoginId() { //basket DB에 저장할 때 사용
		return id;
	}
	
	@Override
	public int memberSave(Member member) { //회원가입 시 데이터 저장
		String sql = "insert into member (memberId, pw) values(?,?)";
		return jdbcTemplate.update(sql, new Object[] {member.getMemberId(),member.getPw()});
	}
	
	@Override
	public Optional<Member> findById(String id) { //id로 회원찾기 => 중복회원 검사 시 사용
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