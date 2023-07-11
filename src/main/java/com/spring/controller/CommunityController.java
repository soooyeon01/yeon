package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.domain.CommunityDTO;
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
	
	@GetMapping("/clist")
	public String CommunityList(Criteria cri, Model model) {
		log.info(model);
		PageMaker pageMaker = new PageMaker(cri, 100);
		model.addAttribute("communityList",service.getAllCommunityByPage(pageMaker));
		model.addAttribute("pageMaker",pageMaker);
		return "community/community";
	}
	
	@GetMapping("/newcommu")
	public String CommunityRegi(CommunityDTO commu) {
		service.registerCommunity(commu);
		return "community/commuRegi";
	}
	
	
}
