package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.domain.MembersDTO;
import com.spring.service.FindEmailService;
import com.spring.service.FindPwdService;
import com.spring.service.JoinService;
import com.spring.service.LoginService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class UserController {
	@Autowired
	private final LoginService service;
	private final JoinService servicej;
	private final FindEmailService servicee;
	private final FindPwdService servicep;
	
	@GetMapping("/login")
	public String login(MembersDTO mdto) {
		service.selectLogin(mdto);
		return "user/login";
	}
	
	@GetMapping("/join")
	public String joinget(MembersDTO mdto) {
		return "user/join";
	}
	
	@PostMapping("/join")
	public String joinpost(MembersDTO mdto) {
		servicej.registerMembers(mdto);
		return "user/join";
	}

	@GetMapping("/findEmail")
	public String findEmail(MembersDTO mdto) {
		servicee.findEmail(mdto);
		return "user/findEmail";
	}
	@GetMapping("/findPwd")
	public String findPwd(MembersDTO mdto) {
		servicep.findPwd(mdto);
		return "user/findEmail";
	}
	
}
