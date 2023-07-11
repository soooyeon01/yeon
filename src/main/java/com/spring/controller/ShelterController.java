package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.service.S_Service;
import com.spring.util.Criteria;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor


public class ShelterController {
	private final S_Service service;
	@GetMapping("/sheldetail")
	public String getS(int shelter_no, Criteria cri, Model model) {
		PageMaker pageMaker = new PageMaker(cri, cri.getAmount()); 
		model.addAttribute("sheldetailList",service.getAllBoardByPage(pageMaker));
		model.addAttribute("pageMaker",pageMaker);
		return "/shel/sheldetail";
	}
	@GetMapping("/shelall")
	public String getAllBoard(Criteria cri, Model model) {
		PageMaker pageMaker = new PageMaker(cri, cri.getAmount()); 
		model.addAttribute("shelList",service.getAllBoardByPage(pageMaker));
		model.addAttribute("pageMaker",pageMaker);
		return "/shel/shelall";
	}
	
}
