package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.service.W_Service;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/with/*")
public class WithpetController {
	private W_Service service;
	@GetMapping("/withdetail")
	public String getW(int with_pet_no) {
		service.getW(with_pet_no);
		return "/with/withdetail";
	}
	@GetMapping("/withall")
	public String getAllBoard() {
		service.getAllBoard();
		return "/with/withall";
	}
	@GetMapping("/withpage")
	public String getAllBoardByPage(PageMaker pageMaker) {
		 , service.getAllBoardByPage(pageMaker);
		return "/with/withpage";
	}
}
