package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.service.W_Service;
import com.spring.util.Criteria;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/with/*")
public class WithpetController {
	private W_Service service;
	@GetMapping("/withdetail")
	public String getW(int with_pet_no, Criteria cri, Model model) {
		PageMaker pageMaker = new PageMaker(cri, cri.getAmount()); 
		model.addAttribute("withdetailList",service.getAllBoardByPage(pageMaker));
		model.addAttribute("pageMaker",pageMaker);
		return "/with/withdetail";
	}
	@GetMapping("/withall")
	public String getAllBoard(Criteria cri, Model model) {
		PageMaker pageMaker = new PageMaker(cri, cri.getAmount()); 
		model.addAttribute("withList",service.getAllBoardByPage(pageMaker));
		model.addAttribute("pageMaker",pageMaker);
		return "/with/withall";
	}
	
}
