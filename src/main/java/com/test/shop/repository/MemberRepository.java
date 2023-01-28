package com.test.shop.repository;

import java.util.Optional;

import com.test.shop.domain.Member;

public interface MemberRepository {
	Member logIn(String id, String pw);
	int memberSave(Member member);
	String getLoginId();
	Optional<Member> findById(String id);
}
