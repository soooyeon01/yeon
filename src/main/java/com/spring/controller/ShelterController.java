package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.service.S_Service;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shel/*")
public class ShelterController {
	private final S_Service service;
	@GetMapping("/sheldetail")
	public String getS(int shelter_no) {
		service.getS(shelter_no);
		return "/shel/sheldetail";
	}
	@GetMapping("/shelall")
	public String getAllBoard() {
		service.getAllBoard();
		return "/shel/shelall";
	}
	@GetMapping("/shelpage")
	public String getAllBoardByPage(PageMaker pageMaker, Model model) {
		model.addAttribute("shelList",service.getAllBoardByPage(pageMaker));
		return "/shel/shelpage";
	}
}
