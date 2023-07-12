package com.spring.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public String loginget(MembersDTO mdto) {
		return "user/login";
	}
	
	@PostMapping("/login")
	public String loginpost(MembersDTO mdto) {
		service.selectLogin(mdto);
		return "user/login";
	}
	
	@GetMapping("/join")
	public String joinget(MembersDTO mdto) {
		return "user/join";
	}
	
	@PostMapping("/join")
	public String joinpost(MembersDTO mdto) {
		int isOk = 1;
		String msg = null;
		if( servicej.registerMembers(mdto) == isOk) {
			msg = "success";
		}else {
			msg = "fail, try again";
		}
		
		return "redirect:/user/login";
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
