package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.domain.P_DTO;
import com.spring.service.P_Service;
import com.spring.util.Criteria;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pet/*")
public class PetnoticeController {
	
	private final P_Service service;
	// localhost:8080/4jojo/pet/petdetail
	
	@GetMapping("/petall")
	public String getPBoard(Criteria cri, Model model) {
		PageMaker pageMaker = new PageMaker(cri, 101); 
		model.addAttribute("petList",service.getAllBoard());
		model.addAttribute("pageMaker",pageMaker);
		return "/pet/pet";
	}
	@GetMapping("/asd")
	public String asd() {
		return "/pet/asd";
	}
	
}
