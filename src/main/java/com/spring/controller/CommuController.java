package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.service.CommunityService;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comm/*")
@Log4j
public class CommuController {
	private final CommunityService service;
	
	@RequestMapping("/list")
	public String Cmain(Model model, PageMaker pageMaker) {
		log.info("CommunityMainPage");
		model.addAttribute("comm",service.getAllCommunityByPage(pageMaker));
		return "comm/comm";
	}
	
	
}
