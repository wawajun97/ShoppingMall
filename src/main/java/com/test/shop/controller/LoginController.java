package com.test.shop.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.shop.domain.Member;
import com.test.shop.service.MemberService;


@Controller
public class LoginController {

	private final MemberService memberservice;
	
	public LoginController(MemberService memberservice) {
		this.memberservice = memberservice;
	}
	
	@GetMapping("login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("memberId") String id, @RequestParam("pw") String pw, Member member) {
		member.setMemberId(id);
		member.setPw(pw);
		
		if(memberservice.logIn(member).equals(Optional.empty())) {
			return "login";
		}
		else {
			return "redirect:searchProduct";
		}
		
	}
}
