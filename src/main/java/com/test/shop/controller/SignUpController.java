package com.test.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.shop.domain.Member;
import com.test.shop.service.MemberService;

@Controller
public class SignUpController {
	
	private final MemberService memberservice;
	
	public SignUpController(MemberService memberservice) {
		this.memberservice = memberservice;
	}
	
	@GetMapping("signup")
	public String signUp() {
		return "signup";
	}
	
	@PostMapping("signup")
	public String PostSignUp(@RequestParam("memberId") String id, @RequestParam("pw") String pw, Member member) {
		member.setMemberId(id);
		member.setPw(pw);
		
		memberservice.signUp(member);
		return "redirect:login";
	}
}
