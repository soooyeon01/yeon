package com.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.domain.MembersDTO;
import com.spring.service.MypageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/mypage/*")
@RequiredArgsConstructor
@Log4j
public class MypageController {
   
   @Autowired
   private MypageService service;

   @RequestMapping("/mypage")
   public String showMypage(HttpServletRequest request, Model model) {
                
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
           
          List<MembersDTO> mdto = service.getMypage(email);
          model.addAttribute("membersDTO", mdto);
          return "/mypage/mypage";
          
         }else {
            return "redirect:/main/main"; 
         }
         }  
   
   
 //pwd
   @GetMapping("/upmypwd")
   public String upmypwd(HttpServletRequest request, Model model, MembersDTO dto) {
                
           HttpSession session = request.getSession();
         boolean SESS_AUTH = false;
         String pwd = request.getParameter("pwd");	
         try {
            SESS_AUTH = (boolean)session.getAttribute("SESS_AUTH");
         }catch(Exception e) {}
         
         if( SESS_AUTH ) {        
      	   if(pwd==null || pwd == "") {
            request.setAttribute("SESS_AUTH", false);
            String email = (String) session.getAttribute("SESS_EMAIL");

	   	       List<MembersDTO> mdto = service.getMypage(email);
	   	       model.addAttribute("membersDTO", mdto);
	   	       
		   	   model.addAttribute("msg", "변경하실 비밀번호를 입력하세요."); 
		   	   model.addAttribute("url", "upmypage");       	   
		   	   return "alert";
		   	   
      	   }else {
      		   service.modifyPwd(dto);
      		   model.addAttribute("msg", "비밀번호 변경이 완료되었습니다."); 
      		   model.addAttribute("url", "upmypage"); 
      		   return "alert";
      	   }    	   
       
 		   }else {
 			   return "redirect:/main/main"; 
 		   }
	   }  
   		
   
 //pwd
   @GetMapping("/upmyphone")
   public String upmyphone(HttpServletRequest request, Model model, MembersDTO dto) {
                
         HttpSession session = request.getSession();
         boolean SESS_AUTH = false;
         String phone = request.getParameter("phone");	
         
         try {
            SESS_AUTH = (boolean)session.getAttribute("SESS_AUTH");
         }catch(Exception e) {}
         
         if( SESS_AUTH ) {        
      	   if(phone==""||phone==null) {
            request.setAttribute("SESS_AUTH", false);
            String email = (String) session.getAttribute("SESS_EMAIL");

	   	       List<MembersDTO> mdto = service.getMypage(email);
	   	       model.addAttribute("membersDTO", mdto);
	   	       
		   	   model.addAttribute("msg", "변경하실 전화번호를 입력하세요."); 
		   	   model.addAttribute("url", "upmypage");   	   
		   	   return "alert";
		   	   
      	   }else {
      		   service.modifyPhone(dto);
      		   model.addAttribute("msg", "비밀번호 변경이 완료되었습니다."); 
      		   model.addAttribute("url", "upmypage"); 
      		   return "alert";
      	   }    	   
       
 		   }else {
 			   return "redirect:/main/main"; 
 		   }
   }  
         

         //페이지연결
         @GetMapping("/upmypage")
         public String upmypage(HttpServletRequest request, Model model, MembersDTO dto) {
                      
               HttpSession session = request.getSession();
               boolean SESS_AUTH = false;
               
               try {
                  SESS_AUTH = (boolean)session.getAttribute("SESS_AUTH");
               }catch(Exception e) {}
               
               if( SESS_AUTH ) {        

                  request.setAttribute("SESS_AUTH", false);
                  String email = (String) session.getAttribute("SESS_EMAIL");

	   	       List<MembersDTO> mdto = service.getMypage(email);
	   	       model.addAttribute("membersDTO", mdto);	   	       	    
	   	   
	   	       return "/mypage/upmypage";
  	       
	   		   }else {
	   			   return "redirect:/main/main"; 
	   		   }
	      
   	}  
	      
	    //withdrawal 탈퇴 페이지 요청
	      @GetMapping("/remM")
	      public String remove() {
	      	return "/mypage/remM";
	      }	      	   
	  	
	      @RequestMapping(value = "/remMC", method = RequestMethod.POST)
	      public String removeC(MembersDTO dto, HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
	          HttpSession session = request.getSession(false);
	          String email = (String) session.getAttribute("SESS_EMAIL");

	          // SESS_EMAIL 값을 사용하여 데이터베이스에서 pwd 값을 가져옴
	          String pwd = service.getPwd(email);
	          
	          //입력값 가져오기
	          String inputpwd = request.getParameter("inputpwd");	          

	          PrintWriter out = response.getWriter();
	          response.setCharacterEncoding("utf-8");
	          boolean SESS_AUTH = false;
	          response.setContentType("text/html; charset=utf-8");

	          try {
	              SESS_AUTH = (boolean) session.getAttribute("SESS_AUTH");
	          } catch (Exception e) {
	          }

	          if (SESS_AUTH) {
	              request.setAttribute("SESS_AUTH", false);

		              if (pwd.equals(inputpwd)) {
		                  service.removeMember(email);
		                  session.invalidate();
		                  model.addAttribute("msg", "탈퇴가 완료되었습니다."); 
		      			model.addAttribute("url", "mypage"); //마이페이지 가면 세션이없어 알아서 메인으로 감.
		      			return "alert";
		                
	
		              } else {
		            	  model.addAttribute("msg", "잘못된 비밀번호 입니다."); 
			      			model.addAttribute("url", "remM"); 
			      			return "alert";
		              }
	          } else {
	              out.println("<script> alert('로그인하세요');");
	              out.close();
	              return "redirect:/main/main";
	          }
	      }
    
	      //정보변경 화면연결
	      @GetMapping("/update")
	      public String update() {
	      	return "/mypage/update";
	      }	
  
}	      
	      
