package com.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
   
   @Autowired
   private MypageService service;

   @RequestMapping("/mypage")
   public String showMypage(HttpServletRequest request, Model model) {
       	   	
	   	  HttpSession session = request.getSession(false);
	      boolean SESS_AUTH = false;
	      
	      try {
	         SESS_AUTH = (boolean)session.getAttribute("SESS_AUTH");
	      }catch(Exception e) {}
	      
	      if( SESS_AUTH ) {
	    	 
//	         request.setCharacterEncoding("utf-8");
	         request.setAttribute("SESS_AUTH", false);
	         String email = (String) session.getAttribute("SESS_EMAIL");
//	         session.setAttribute("id", email);
	   
		  
	       List<MembersDTO> mdto = service.getMypage(email);
	       model.addAttribute("membersDTO", mdto);
	       return "/mypage/mypage";
	       
		   }else {
			   return "redirect:/main/main"; 
		   }
   		}  
   
 

//   @RequestMapping("/upmember")
//   public void upMember(Model model) {
//   
//       model.addAttribute("upmember",service.selectMypage());
//      
//   }
//   
   
}

   
// 