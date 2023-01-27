package com.test.shop.service;

import java.util.Optional;

import com.test.shop.domain.Member;
import com.test.shop.repository.MemberRepository;

public class MemberService {

	private final MemberRepository memberRepository;
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public String signUp(Member member) {
		validateDuplicateMember(member);
		memberRepository.memberSave(member);
		return member.getMemberId();
	}
	
	public Optional<Member> logIn(Member member) {
		return memberRepository.logIn(member.getMemberId(), member.getPw());
	}
	
	public String getLoginId() {
		return memberRepository.getLoginId();
	}
	
	private void validateDuplicateMember(Member member) {
		memberRepository.findById(member.getMemberId())
		.ifPresent(m -> {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
			});
		}
}
