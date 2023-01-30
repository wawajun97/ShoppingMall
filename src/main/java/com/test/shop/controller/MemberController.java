package com.test.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.shop.domain.Member;
import com.test.shop.service.MemberService;


@Controller
public class MemberController {

	private final MemberService memberservice;

	public MemberController(MemberService memberservice) {
		this.memberservice = memberservice;
	}
	
	@GetMapping("login")
	public String login() {
		return "login";
	}
	
	@PostMapping("login")
	public String login(@RequestParam("memberId") String id, @RequestParam("pw") String pw) {
		Member member = new Member();
		member.setMemberId(id);
		member.setPw(pw);

		return memberservice.logIn(member);		
	}
	
	@GetMapping("signup")
	public String signUp() {
		return "signup";
	}
	
	@PostMapping("signup")
	public String PostSignUp(@RequestParam("memberId") String id, @RequestParam("pw") String pw) {
		Member member = new Member();
		member.setMemberId(id);
		member.setPw(pw);
		
		memberservice.signUp(member);
		return "redirect:login";
	}
}
