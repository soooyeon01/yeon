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
import com.spring.domain.LikeDTO;
import com.spring.domain.MembersDTO;
import com.spring.domain.ReplyDTO;
import com.spring.service.CommunityService;
import com.spring.service.LikeService;
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
	private final LikeService lservice;
	/*
	 * @RequestMapping("/clist") 
	 * public String CommunityList(Model model,Criteria
	 * cri) { log.info(model); PageMaker pageMaker = new PageMaker(cri, 100);
	 * model.addAttribute("communityList",service.getAllCommunityByPage(pageMaker));
	 * model.addAttribute("pageMaker",pageMaker); return "community/community"; }
	 */
	
	@PostMapping("/newR")
	@ResponseBody		
	public Map<String, String> newR(@RequestBody ReplyDTO rdto, HttpSession session) {

		Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
        System.out.println("댓글 등록 통신 성공");
        
        Map<String, String> map = new HashMap<>(); 
        if(SESS_AUTH != null && SESS_AUTH) {
//          request.setCharacterEncoding("utf-8");

            String email = (String) session.getAttribute("SESS_EMAIL");
            String nickname = (String) session.getAttribute("SESS_NICKNAME");
            
            log.info("로긘함. 스크랩 진행");
			
			rservice.registerReply(rdto);
			log.info("댓글 등록 서비스 성공");
			
			map.put("result", "newSuccess");
			
        } else {
        	map.put("result", "fail");
		}
        return map;
	}
	
	@PostMapping("/delR")
	@ResponseBody		
	public Map<String, String> DeleteR(@RequestBody ReplyDTO rdto, HttpSession session) {
		Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
        System.out.println("댓글 삭제 통신 성공");
        
        Map<String, String> map = new HashMap<>(); 
        if(SESS_AUTH != null && SESS_AUTH) {
//          request.setCharacterEncoding("utf-8");
            String email = (String) session.getAttribute("SESS_EMAIL");
            String nickname = (String) session.getAttribute("SESS_NICKNAME");
            
            log.info("로그인 유지중...");
			
			rservice.removeReply(rdto);
			log.info("댓글 삭제 서비스 성공");
			
			map.put("result", "delSuccess");
			
        } else {
        	map.put("result", "fail");
		}
        return map;
	}

	
	// localhost:8080/4jojo/community/replyList/{c_no}
	@GetMapping("/reply/{c_no}")
	@ResponseBody
	public Map<String, Object> getList(@PathVariable int c_no, Model model) {
		log.info("댓글 목록 컨트롤러 동작");
		List<ReplyDTO> list = rservice.getReplyList(c_no);
		List<ReplyDTO> list2 = rservice.getReplyList(c_no);
		log.info("댓글리스트"+list);
		model.addAttribute("list2",list2);
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
	public String CommunityList(HttpSession session, Model model, LikeDTO ldto) {

		Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
         
        if(SESS_AUTH != null && SESS_AUTH) {
//          request.setCharacterEncoding("utf-8");
            String email = (String) session.getAttribute("SESS_EMAIL");
            String nickname = (String) session.getAttribute("SESS_NICKNAME");
            model.addAttribute("viewCntList", service.getViewCntCommunity());
            model.addAttribute("communityList",service.getAllCommunity());
            model.addAttribute("likeCntList", service.getLikeCntCommunity());
            
            //model.addAttribute("likeCnt", lservice.getLikeCnt(ldto));
            return "community/community";
        }else {
			return "redirect:/main/main";
        }
        
	}
	
	@RequestMapping("/myclist")
	public String MyCommunList(HttpSession session, Model model, String nickname) {
		Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
		
        if(SESS_AUTH != null && SESS_AUTH) {
//          request.setCharacterEncoding("utf-8");
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
	public String CommunityRegi(HttpSession session, CommunityDTO commu, MembersDTO mdto) {
		Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
		
        if(SESS_AUTH != null && SESS_AUTH) {
//          request.setCharacterEncoding("utf-8");

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
	public String CommuSel(HttpSession session, Model model, int c_no, LikeDTO ldto) {
		Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
         
        if(SESS_AUTH != null && SESS_AUTH) {
	//      request.setCharacterEncoding("utf-8");

	        String email = (String) session.getAttribute("SESS_EMAIL");
	        String nickname = (String) session.getAttribute("SESS_NICKNAME");
	//      session.setAttribute("id", email);
			CommunityDTO selectone=service.getCommunity(c_no);
			model.addAttribute("selectone", selectone);
			service.viewCnt(c_no);
			
			ldto=new LikeDTO();
			ldto.setC_no(c_no);
			ldto.setNickname(nickname);
			log.info("여기1 " + lservice.getLikeCnt(ldto));
			model.addAttribute("ldto", lservice.findLike(ldto));
			model.addAttribute("getLikeCnt", lservice.getLikeCnt(ldto));
			log.info("여기2 " + lservice.getLikeCnt(ldto));
			
			return "community/commuSel";
        }else {
        	return "redirect:/main/main";
        }	
	}
	
	@PostMapping("/likeUp")
	@ResponseBody 
	public void likeup(@RequestBody LikeDTO ldto) {
		log.info("좋아요 컨트롤러 연결 성공");
		log.info(ldto.getC_no());
		log.info(ldto.getNickname());
		lservice.likeUp(ldto);
	
	}
	
	@PostMapping("/likeDown")
	@ResponseBody
	public void likeDown(@RequestBody LikeDTO ldto) {
		log.info("좋아요 싫어요!");
		
		lservice.likeDown(ldto);
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
