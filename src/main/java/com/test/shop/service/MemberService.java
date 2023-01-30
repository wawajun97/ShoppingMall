package com.test.shop.service;

import java.util.NoSuchElementException;
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
	
	public String logIn(Member member) {
		Member result = memberRepository.logIn(member.getMemberId(), member.getPw());
		if(result.equals("")) {
			inValidateDuplicateMember(result);
			return "login";
		}
		else {
			return "redirect:searchProduct";
		}
	}
	
	public String getLoginId() {
		return memberRepository.getLoginId();
	}
	
	private void validateDuplicateMember(Member member) {
		memberRepository.findById(member.getMemberId())
		.ifPresent(m -> {
			throw new IllegalStateException("이미 존재하는 회원입니다."); //객체 상태가 메소드를 처리하기에 적절 X
			});
		}

	private void inValidateDuplicateMember(Member member) {
		if(memberRepository.findById(member.getMemberId())
				.isEmpty()){
			throw new NoSuchElementException(); //존재하지 않는 것을 가져오려고 할 때
		};
	}
}
