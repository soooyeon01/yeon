package com.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.domain.CommunityDTO;
import com.spring.domain.MembersDTO;
import com.spring.domain.ReplyDTO;
import com.spring.service.CommunityService;
import com.spring.service.LoginService;
import com.spring.service.ReplyService;
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
	private final ReplyService rservice;
	/*
	 * @RequestMapping("/clist") 
	 * public String CommunityList(Model model,Criteria
	 * cri) { log.info(model); PageMaker pageMaker = new PageMaker(cri, 100);
	 * model.addAttribute("communityList",service.getAllCommunityByPage(pageMaker));
	 * model.addAttribute("pageMaker",pageMaker); return "community/community"; }
	 */
	
	@PostMapping("/newR")
	public String InsertComment(@RequestBody ReplyDTO rdto, HttpServletRequest request) {
		HttpSession session = request.getSession();
        boolean SESS_AUTH = false;
        System.out.println("댓글 등록 통신 성공");
        try {
            SESS_AUTH = (boolean)session.getAttribute("SESS_AUTH");
        }catch(Exception e) {}
         
        if( SESS_AUTH ) {
//          request.setCharacterEncoding("utf-8");
            request.setAttribute("SESS_AUTH", false);
            String email = (String) session.getAttribute("SESS_EMAIL");
            String nickname = (String) session.getAttribute("SESS_NICKNAME");
            
            log.info("로긘함. 스크랩 진행");
			
			rservice.registerReply(rdto);
			log.info("댓글 등록 서비스 성공");
			return "newSuccess";
        } else {
			return "fail";
		}
	}
	// localhost:8080/4jojo/community/replyList/{c_no}
	@GetMapping("/reply/{c_no}")
	public Map<String, Object> getList(@PathVariable int c_no, Model model) {
		log.info("댓글 목록 컨트롤러 동작");
		List<ReplyDTO> list = rservice.getReplyList(c_no);
		int total = rservice.cntTotal(c_no);
		model.addAttribute("total",total);
		
		//ModelAndView view = new ModelAndView();
		log.info("댓글 갯수 " + rservice.cntTotal(c_no));
		//view.setViewName("/community/commuSel");
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		
		return map;
	}
	
	@RequestMapping("/clist")
	public String CommunityList(Model model) {
		
		model.addAttribute("communityList",service.getAllCommunity());
		return "community/community";
	}
	
	@RequestMapping("/myclist")
	public String MyCommunList(HttpServletRequest request, Model model, String nickname) {
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
//          session.setAttribute("id", email);
            model.addAttribute("myCommuList",service.getMyCommunity(nickname));
            return "community/mycommunity";
        }else {
        	return "redirect:/main/main";
        }
	}
	
	@GetMapping("/newCommu")
	public String moveRegi() {
		return "community/commuRegi";
	}
	
	@RequestMapping("/newCommu")
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
	public String CommuSel(HttpServletRequest request, Model model, int c_no, String nickname) {
		HttpSession session = request.getSession();
        boolean SESS_AUTH = false;
         
        try {
            SESS_AUTH = (boolean)session.getAttribute("SESS_AUTH");
        }catch(Exception e) {}
         
        if( SESS_AUTH ) {
           
	//      request.setCharacterEncoding("utf-8");
	        request.setAttribute("SESS_AUTH", false);
	        String email = (String) session.getAttribute("SESS_EMAIL");
	//      session.setAttribute("id", email);
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
