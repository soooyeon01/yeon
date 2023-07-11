package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.service.CommunityService;
import com.spring.util.Criteria;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/community/*")
@RequiredArgsConstructor
@Log4j
public class CommunityController {

	private final CommunityService service;
	
	@GetMapping("/community")
	public String CommunityList(Criteria cri, Model model) {
		log.info(model);
		PageMaker pageMaker = new PageMaker(cri, 100);
		model.addAttribute("communityList",service.getAllCommunity());
		return "community/community";
	}
}
