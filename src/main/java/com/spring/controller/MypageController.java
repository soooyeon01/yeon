package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.domain.MembersDTO;
import com.spring.service.MypageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/mypage/*")
@RequiredArgsConstructor
public class MypageController {
	
	private final MypageService service;
	
	@GetMapping("/Mlist")
	public String selectMypage(String email) {
		service.getMypage(email); 
		return "/Mlist";
	}

	@GetMapping("/Mupdate")
	public String updateMypage(MembersDTO mdto) {
		service.modifyMember(mdto);
		return "/Mupdate";
	}
	
}
