package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.domain.MembersDTO;
import com.spring.service.LoginService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@Controller
@RequestMapping("/login/*")
@RequiredArgsConstructor
public class LoginController {
	
	private final LoginService service;
	
	@PostMapping("/login")
	public String login(MembersDTO mdto) {
		service.getMembersByIdAndPwd(mdto);
		return "/login";
	}

}
