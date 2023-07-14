package com.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
   
   
   @GetMapping("/upmypwd")
   public String updatePwd(HttpServletRequest request, Model model, MembersDTO dto) {
                
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
                  
          service.modifyPwd(dto);
         
          return "redirect:/mypage/upmypage";
          
         }else {
            return "redirect:/main/main"; 
         }
   }     
         //phone
         @GetMapping("/upmyphone")
         public String updatePhone(HttpServletRequest request, Model model, MembersDTO dto) {
                
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
                  
          service.modifyPhone(dto);
      
          return "redirect:/mypage/upmypage";
          
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
   
         
         //회원탈퇴
         @GetMapping("/remove")
         public String removeMember(HttpServletRequest request, Model model, MembersDTO dto) {
                      
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
                
                service.removeMember(dto);
                   session.invalidate(); 
                
                return "redirect:/main/main";
                
               }else {
                  return "redirect:/mypage/removeM"; 
               }
         
      }           
         
         //회원탈퇴 확인창
         @GetMapping("/removecheck")
         public String removeCheck(HttpServletRequest request, Model model, MembersDTO dto) {
                      
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
            
         
      }  
            return "/mypage/removeM";    
}
         
   
}

   
// 