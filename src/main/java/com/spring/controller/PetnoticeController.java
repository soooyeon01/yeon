package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.domain.P_DTO;
import com.spring.service.P_Service;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pet/*")
public class PetnoticeController {
	private final P_Service service;
	@GetMapping("/petdeatil")
	public String getP(int pet_notice_no, Model model) {
		service.getP(pet_notice_no);
		return "/pet/petdetail";
	}
	@GetMapping("/petall")
	public String getPBoard() {
		service.getAllBoard();
		return "/pet/petall";
	}
	@GetMapping("/petpage")
	public String getPBoardByPage(PageMaker pageMaker, Model model) {
		model.addAttribute("petList",service.getAllBoardByPage(pageMaker));
		model.addAttribute("pageMaker",pageMaker);
		return "/pet/petpage";
	}
}
