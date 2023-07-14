package com.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.domain.CommunityDTO;
import com.spring.domain.MembersDTO;
import com.spring.service.CommunityService;
import com.spring.service.LoginService;
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
	private final LoginService logservice;
	
	/*
	 * @RequestMapping("/clist") 
	 * public String CommunityList(Model model,Criteria
	 * cri) { log.info(model); PageMaker pageMaker = new PageMaker(cri, 100);
	 * model.addAttribute("communityList",service.getAllCommunityByPage(pageMaker));
	 * model.addAttribute("pageMaker",pageMaker); return "community/community"; }
	 */
	
	@RequestMapping("/clist")
	public String CommunityList(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
        boolean SESS_AUTH = false;
         
        try {
            SESS_AUTH = (boolean)session.getAttribute("SESS_AUTH");
        }catch(Exception e) {}
         
        if( SESS_AUTH ) {
			/*
			 * mdto.setNickname(nickname); session.setAttribute("SESS_NICKNAME",
			 * mdto.getNickname()); logservice.selectLogin(mdto);
			 */
//          request.setCharacterEncoding("utf-8");
            request.setAttribute("SESS_AUTH", false);
            String email = (String) session.getAttribute("SESS_EMAIL");
            String nickname = (String) session.getAttribute("SESS_NICKNAME");
		model.addAttribute("communityList",service.getAllCommunity());
		return "community/community";
        }else {
        	return "redirect:/main/main";
        }
	}
	
	@RequestMapping("/myclist")
	public String MyCommunList(Model model) {
		model.addAttribute("myCommuList",service.getMyCommunity());
		return "community/mycommunity";
	}
	
	@GetMapping("/newcommu")
	public String moveRegi() {
		return "community/commuRegi";
	}
	
	@RequestMapping("/newcommu")
	public String CommunityRegi(HttpServletRequest request, CommunityDTO commu, MembersDTO mdto) {
		HttpSession session = request.getSession();
        boolean SESS_AUTH = false;
         
        try {
            SESS_AUTH = (boolean)session.getAttribute("SESS_AUTH");
        }catch(Exception e) {}
         
        if( SESS_AUTH ) {
//          request.setCharacterEncoding("utf-8");
            request.setAttribute("SESS_AUTH", false);
            String email = (String) session.getAttribute("SESS_EMAIL");
            String nickname = (String) session.getAttribute("SESS_NICKNAME");
//          session.setAttribute("id", email);
            
			int result=service.registerCommunity(commu);
			if(result>0) {
				return "redirect:/community/clist";
			}else {
				return "redirect:/community/commuRegi";
			}
        }else {
        	return "redirect:/main/main";
        }
    }
	
	@GetMapping("/commuSel")
	public String CommuSel(HttpServletRequest request, Model model,int c_no, String nickname) {
		HttpSession session = request.getSession();
        boolean SESS_AUTH = false;
         
        try {
            SESS_AUTH = (boolean)session.getAttribute("SESS_AUTH");
        }catch(Exception e) {}
         
        if( SESS_AUTH ) {
           
//            request.setCharacterEncoding("utf-8");
            request.setAttribute("SESS_AUTH", false);
            String email = (String) session.getAttribute("SESS_EMAIL");
//            session.setAttribute("id", email);
		CommunityDTO selectone=service.getCommunity(c_no);
		model.addAttribute("selectone", selectone);
		service.viewCount(c_no);
		return "community/commuSel";
        }else {
        	return "redirect:/main/main";
        }
			
	}
	
	@RequestMapping("/commuUp1")
	public String CommunityUdt(Model model, int c_no, String nickname) {
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
	
	@RequestMapping("/Pwd")
	public String pwd() {
		return "community/mypagePwd";	
	}
	
	
}
