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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
   public String showMypage(HttpSession session, Model model) {
                
	   Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");

         if(SESS_AUTH != null && SESS_AUTH) {
           
          String email = (String) session.getAttribute("SESS_EMAIL");
           
          List<MembersDTO> mdto = service.getMypage(email);
          model.addAttribute("membersDTO", mdto);
          return "/mypage/mypage";
          
         }else {
            return "redirect:/main/main"; 
         }
         }  
   
   
 //pwd
   @PostMapping("/upmypwd")
   public String upmypwd(HttpSession session, Model model, MembersDTO dto,@RequestParam("pwd")String pwd
		   , @RequestParam("newpwd")String newpwd,@RequestParam("newcpwd")String newcpwd) {
 
	   Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
       String email = (String) session.getAttribute("SESS_EMAIL");
 	   List<MembersDTO> mdto = service.getMypage(email);
 	   model.addAttribute("membersDTO", mdto);
 	     
 	   String nowpwd = service.getPwd(email); //현재비밀번호

         if( SESS_AUTH != null && SESS_AUTH ) {     
 
      	   if(pwd==null || pwd == "" || newpwd==null ||newpwd == "" || newcpwd==null || newcpwd == "") {           
		   	   model.addAttribute("msg", "입력이 완료되지 않았습니다."); 
		   	   model.addAttribute("url", "upmypage");       	   
		   	   return "alert";		   	   
		   	   
      	   }else if(pwd.equals(nowpwd) && !newpwd.equals(newcpwd)){
      			model.addAttribute("msg", "입력하신 새 비밀번호가 일치하지 않습니다."); 
      			model.addAttribute("url", "upmypage"); 
      			 return "alert";	
      			
      	   }else if(!pwd.equals(nowpwd) && newpwd.equals(newcpwd)){
     			model.addAttribute("msg", "현재 비밀번호가 일치하지 않습니다."); 
     			model.addAttribute("url", "upmypage"); 
     			 return "alert";	
     			
     	   }else if(pwd.equals(nowpwd) && newpwd.equals(newcpwd)){
     		   dto.setPwd(newpwd);
     		   service.modifyPwd(dto);
      		   model.addAttribute("msg", "비밀번호 변경이 완료되었습니다."); 
      		   model.addAttribute("url", "upmypage"); 
      		   return "alert";
      		   
      	   }
	   }  else {
			   return "redirect:/main/main"; 
		   }
		return null;
   }	
   
 //phone
   @PostMapping("/upmyphone")
   public String upmyphone(HttpSession session, Model model, MembersDTO dto,@RequestParam("phone")String phone) {
     

	   Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
      if (SESS_AUTH != null && SESS_AUTH) {

         if (phone == null || phone.isEmpty()) {
        	 
            String email = (String) session.getAttribute("SESS_EMAIL");

            List<MembersDTO> mdto = service.getMypage(email);
            model.addAttribute("membersDTO", mdto);

            model.addAttribute("msg", "변경하실 전화번호를 입력하세요.");
            model.addAttribute("url", "upmypage");
            return "alert";

         } else {
            // 전화번호 입력값이 있을 경우 처리
            int phone2 = 0;
            try {
               phone2 = Integer.parseInt(phone);
               dto.setPhone(phone2);
            } catch (NumberFormatException e) {
               // 전화번호 입력값이 int로 변환할 수 없는 경우 처리
               e.printStackTrace();
            }
            service.modifyPhone(dto);
            model.addAttribute("msg", "비밀번호 변경이 완료되었습니다.");
            model.addAttribute("url", "upmypage");
            return "alert";
         }

      } else {
         return "redirect:/main/main";
      }
   }

         //페이지연결
         @RequestMapping("/upmypage")
         public String upmypage(HttpSession session, Model model, MembersDTO dto) {
              
               Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
               if( SESS_AUTH != null && SESS_AUTH ) {        
                 
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
	      public String removeC(MembersDTO dto, HttpServletResponse response, HttpSession session, Model model
	    		  				,@RequestParam("inputpwd")String inputpwd) throws IOException {
	    	 
	    	  Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
	          String email = (String) session.getAttribute("SESS_EMAIL");

	          // SESS_EMAIL 값을 사용하여 데이터베이스에서 pwd 값을 가져옴
	          String pwd = service.getPwd(email);         

	          PrintWriter out = response.getWriter();
	          response.setCharacterEncoding("utf-8");
	          response.setContentType("text/html; charset=utf-8");


	          if (SESS_AUTH != null && SESS_AUTH) {

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
	              out.println("<script> alert('로그인이 필요합니다.');");
	              out.close();
	              return "redirect:/main/main";
	          }
	      }
    
	    //회원정보확인 화면연결
	      @GetMapping("/updatecheck")
	      public String updatecheck() {
	      	return "/mypage/updatecheck";
	      }	
	      
	      //회원정보확인 input 처리
	      @RequestMapping(value = "/updatecheck", method = RequestMethod.POST)
	      public String updatecheck(HttpServletResponse response,HttpSession session, Model model
	    		  					,@RequestParam("inputpwd")String inputpwd) {
	    	  
	    	  Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
	          String email = (String) session.getAttribute("SESS_EMAIL");

	          // SESS_EMAIL 값을 사용하여 데이터베이스에서 pwd 값을 가져옴
	          String pwd = service.getPwd(email);
	          
	          response.setCharacterEncoding("utf-8");
	          response.setContentType("text/html; charset=utf-8");	      

	          if (SESS_AUTH != null && SESS_AUTH) {	           

		              if (pwd.equals(inputpwd)) {
		            	List<MembersDTO> mdto = service.getMypage(email);
		   	   	       model.addAttribute("membersDTO", mdto);	   	    
		      			return "/mypage/upmypage";

		              } else {
		            	  	model.addAttribute("msg", "잘못된 비밀번호 입니다."); 
			      			model.addAttribute("url", "updatecheck"); 
			      			return "alert";
		              }
	          } else {
	        	  model.addAttribute("msg", "로그인이 필요합니다."); 
	      		model.addAttribute("url", "login"); 
	              return "alert";
	          }
	      }
	      
	      
	    
	      
	     
	      
  
}	      
	      
