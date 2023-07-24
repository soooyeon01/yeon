package com.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
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
	public String getSBoard(HttpServletRequest request, Model model, Criteria cri, String nickname) {
		HttpSession session = request.getSession();
        boolean SESS_AUTH = false;
         
        try {
            SESS_AUTH = (boolean)session.getAttribute("SESS_AUTH");
        }catch(Exception e) {}
         
        if( SESS_AUTH ) {
//          request.setCharacterEncoding("utf-8");
            request.setAttribute("SESS_AUTH", false);
            String email = (String) session.getAttribute("SESS_EMAIL");
            nickname = (String) session.getAttribute("SESS_NICKNAME");
		int totalCount=services.getCountAllBoard();
		PageMaker pageMaker = new PageMaker(cri,totalCount);
		model.addAttribute("favorites",services.getSBoardByPage(nickname,pageMaker));
		model.addAttribute("pageMaker",pageMaker);
		return "/fa/favorites";
        }else {
        	return "redirect:/main/main";
        }
	}
	@GetMapping("/favoritew")
	public String getWBoard(HttpServletRequest request, Model model, Criteria cri, String nickname) {
		HttpSession session = request.getSession();
        boolean SESS_AUTH = false;
         
        try {
            SESS_AUTH = (boolean)session.getAttribute("SESS_AUTH");
        }catch(Exception e) {}
         
        if( SESS_AUTH ) {
//          request.setCharacterEncoding("utf-8");
            request.setAttribute("SESS_AUTH", false);
            String email = (String) session.getAttribute("SESS_EMAIL");
            nickname = (String) session.getAttribute("SESS_NICKNAME");
		int totalCount=servicew.getCountAllBoard();
		PageMaker pageMaker = new PageMaker(cri,totalCount);
		model.addAttribute("favoritew",servicew.getWBoardByPage(nickname,pageMaker));
		model.addAttribute("pageMaker",pageMaker);
		return "/fa/favoritew";
        }else {
        	return "redirect:/main/main";
        }
	}
	@RequestMapping("/favoritep")
	public String getPBoard(HttpServletRequest request, Model model, Criteria cri, String nickname) {
		HttpSession session = request.getSession();
        boolean SESS_AUTH = false;
         
        try {
            SESS_AUTH = (boolean)session.getAttribute("SESS_AUTH");
        }catch(Exception e) {}
         
        if( SESS_AUTH ) {
//          request.setCharacterEncoding("utf-8");
            request.setAttribute("SESS_AUTH", false);
            String email = (String) session.getAttribute("SESS_EMAIL");
            nickname = (String) session.getAttribute("SESS_NICKNAME");
			int totalCount=servicep.getCountAllBoard();
			PageMaker pageMaker = new PageMaker(cri,totalCount);
			model.addAttribute("favoritep",servicep.getPBoardByPage(nickname,pageMaker));
			model.addAttribute("pageMaker",pageMaker);
			return "/fa/favoritep";
	        }else {
	        	return "redirect:/main/main";
			}
		}
		
}
