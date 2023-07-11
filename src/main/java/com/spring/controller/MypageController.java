package com.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.domain.MembersDTO;
import com.spring.mapper.MypageMapper;
import com.spring.service.MypageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/mypage/*")
@RequiredArgsConstructor
public class MypageController {
	
	
	private final MypageService service;
	
	@Autowired
	private MypageMapper mapper;

	@RequestMapping("/mypage")
	public String showMypage(HttpSession session, Model model) {
	    String id = (String) session.getAttribute("SESS_EMAIL");
	    List<MembersDTO> dto = mapper.selectMypage(id);
	    model.addAttribute("memberDTO", dto);
	    return "mypage/mypage";
	}
	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String handleGetRequest(HttpServletRequest request, HttpSession session, Model model) {
//	    
//		boolean sessAuth = false;
//	    
//	    try {
//	        sessAuth = (boolean) session.getAttribute("SESS_AUTH");
//	    } catch (Exception e) {}
//	    
//	    if (sessAuth) {
//
//	        session.setAttribute("SESS_AUTH", false);
//	        
//	        MembersDTO mdto = new MembersDTO();
//	        String email = (String) session.getAttribute("SESS_EMAIL");	     
//	        
//	        model.addAttribute("mdto", mdto);
//	        
//	        return "/view/mypage";
//	    } else {
//	        return "redirect:/login"; // 로그인 페이지로 리다이렉트
//	    }
//	}
//
//	
//	@GetMapping("/mlist")
//	public String selectMypage(String email) {
//		service.getMypage(email); 
//		return "/mypage/mypage";
//	}

	@GetMapping("/mupdate")
	public String updateMypage(MembersDTO mdto) {
		service.modifyMember(mdto);
		return "/mupdate";
	}
	
	@GetMapping("/test")
	public String test() {
		return "/mypage/test";
	}
}
