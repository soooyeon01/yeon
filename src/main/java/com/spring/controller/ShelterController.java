package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.domain.S_DTO;
import com.spring.service.S_Service;
import com.spring.util.Criteria;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor

@RequestMapping("/shel/*")
public class ShelterController {
	private final S_Service service;
	@GetMapping("/sheldetail")
	public String getS(int shelter_no, Model model) {
		
		model.addAttribute("sheldetailList",service.getS(shelter_no));
		return "/shel/sheldetail";
	}
	@GetMapping("/shelall")
	public String getAllBoard(Criteria cri, Model model) {
		
		PageMaker pageMaker = new PageMaker(cri, 101); 
		model.addAttribute("shelList",service.getAllBoardByPage(pageMaker));
		model.addAttribute("pageMaker",pageMaker);
		return "/shel/shel";
	}
	
	@PostMapping("/registershel")
	public String insertF_S(S_DTO dto) {
		service.registerShelterData(dto);
		return "/shel/sheldetail";
	}
	
}
