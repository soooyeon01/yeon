package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	/*
	 * @RequestMapping("/clist") 
	 * public String CommunityList(Model model,Criteria
	 * cri) { log.info(model); PageMaker pageMaker = new PageMaker(cri, 100);
	 * model.addAttribute("communityList",service.getAllCommunityByPage(pageMaker));
	 * model.addAttribute("pageMaker",pageMaker); return "community/community"; }
	 */
	
	@RequestMapping("/clist")
	public String CommunityList(Model model) {
		model.addAttribute("communityList",service.getAllCommunity());
		return "community/community";
	}
	
	@GetMapping("/newcommu")
	public String moveRegi() {
		return "community/commuRegi";
	}
	
	@RequestMapping("/newcommu")
	public String CommunityRegi(CommunityDTO commu) {
		
		int result=service.registerCommunity(commu);
		if(result>0) {
			return "redirect:community/clist";
		}else {
		return "redirect:/commuRegi";
		}
	}
	
	@GetMapping("/commuSel")
	public String CommuSel(Model model,int c_no) {
		model.addAttribute("select", service.getCommunity(c_no));
		return "community/commuSel";
	}
	
	@RequestMapping("/commuUp1")
	public String CommunityUdt(Model model, int c_no) {
		model.addAttribute("selectone",service.getCommunity(c_no));
			return "community/commuUp";
		}
	
	@PostMapping("/commuUp")
	public String Updt(CommunityDTO commu) {
	int	result=service.modifyCommunity(commu);
		if(result>0) {
			return "redirect:/community/commuSel?c_no="+commu.getC_no();
		}else {
			return "redirect:/community/commuUp?c_no="+commu.getC_no();
		}
	}
	
	@RequestMapping("/commuDel")
	public String CommuDel(int c_no) {
	int	result=service.removeCommunity(c_no);
		if(result>0) {
			return "redirect:/community/clist";
		}else {
			return "redirect:/community/commuSel?c_no="+c_no;
		}
	}

}
