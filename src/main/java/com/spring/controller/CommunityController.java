package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.service.CommunityService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/community/*")
@RequiredArgsConstructor
@Log4j
public class CommunityController {
	@Autowired
	private CommunityService service;
	
	@RequestMapping("communityList")
	public void CommunityList(Model model) {
		log.info(model);
		model.addAttribute("communityList",service.getAllCommunity());
	}
}
