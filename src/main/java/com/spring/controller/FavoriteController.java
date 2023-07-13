package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.service.F_P_Service;
import com.spring.service.F_S_Service;
import com.spring.service.F_W_Service;
import com.spring.util.Criteria;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/fa/*")
@RequiredArgsConstructor
public class FavoriteController {
	private final F_S_Service services;
	private final F_W_Service servicew;
	private final F_P_Service servicep;
	@GetMapping("/favorites")
	public String getSBoard(Model model, Criteria cri) {
		int totalCount=services.getCountAllBoard();
		PageMaker pageMaker = new PageMaker(cri,totalCount);
		model.addAttribute("favorites",services.getSBoardByPage(pageMaker));
		model.addAttribute("pageMaker",pageMaker);
		return "/fa/favorites";
	}
	@GetMapping("/favoritew")
	public String getWBoard(Model model, Criteria cri) {
		int totalCount=servicew.getCountAllBoard();
		PageMaker pageMaker = new PageMaker(cri,totalCount);
		model.addAttribute("favoritew",servicew.getWBoardByPage(pageMaker));
		model.addAttribute("pageMaker",pageMaker);
		return "/fa/favoritew";
	}
	@GetMapping("/favoritep")
	public String getPBoard(Model model, Criteria cri) {
		int totalCount=servicep.getCountAllBoard();
		PageMaker pageMaker = new PageMaker(cri,totalCount);
		model.addAttribute("favoritep",servicep.getPBoardByPage(pageMaker));
		model.addAttribute("pageMaker",pageMaker);
		return "/fa/favoritep";
	}
	
	
	
}
