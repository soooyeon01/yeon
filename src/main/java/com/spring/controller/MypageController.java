package com.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
     
         //하는 중
         @RequestMapping("/remMC")
         public String removeC( HttpServletResponse response,HttpServletRequest request, Model model, MembersDTO dto) throws IOException{
                      
            HttpSession session = request.getSession(false);
            String inputpwd = request.getParameter("inputpwd");
            String pwd = (String)session.getAttribute("SESS_PWD");
            log.info("인풋"+inputpwd);
            log.info("인풋"+pwd);
            PrintWriter out = response.getWriter();
            response.setCharacterEncoding("utf-8");
            boolean SESS_AUTH = false;
            
            response.setContentType("text/html; charset=utf-8");
            
               try {
                  SESS_AUTH = (boolean)session.getAttribute("SESS_AUTH");
               }catch(Exception e) {}
               
               if( SESS_AUTH ) {
                  request.setAttribute("SESS_AUTH", false);                  
                  session.setAttribute("pw", pwd);
                    if(pwd.equals(inputpwd)) {
                       
                       log.info("여기"+inputpwd);

                       service.removeMember(dto);
                       session.invalidate(); 
                    
                       out.println("<script> alert('탈퇴가 완료되었습니다.');");      
                       out.println("location.href='/main/main'; </script>");
                       out.close();
                       
                    return null;
                    
                    }else {
                       out.println("<script> alert('입력하신 정보가 맞지 않습니다.');");
                  
                       out.println("location.href='/mypage/remM'; </script>");
                       out.close();
                       
                  return null;
             }
            }else {
                     out.println("<script> alert('로그인하세요');");
                       out.close();
                    return "/main/main";
            }
         
}     
         
         
         
//    //   withdrawal 탈퇴 페이지 확인 요청
//         @RequestMapping(value = "/remMC", method = RequestMethod.POST)
//         public String Check( Model model,HttpServletRequest request, HttpServletResponse response, MembersDTO dto) throws IOException{
//            System.out.println("안녕");
//            HttpSession session = request.getSession();
//            String pwd = (String)session.getAttribute("SESS_PWD");
//            String inputpwd = request.getParameter("input");            
//            
//            PrintWriter out = response.getWriter();
//            response.setCharacterEncoding("utf-8");
//            response.setContentType("text/html; charset=utf-8");
//            boolean SESS_AUTH = false;               
//            try {
//                     SESS_AUTH = (boolean)session.getAttribute("SESS_AUTH");
//                  }catch(Exception e) {}
//            
//            if(SESS_AUTH) {
//               if(inputpwd.equals(pwd)) {                
//                   out.println("<script> alert('탈퇴가 완료되었습니다.');");      
//                   out.close();
//                   return "redirect:/main/main"; 
//               }else {
//                  //로그인 실패 
//                  out.println("<script> alert('입력하신 정보가 맞지 않습니다.');");
//                  out.println("history.go(-1); </script>");
//                  out.close();
//                  
//                  return "redirect:/mypage/remM";
//               }
//            }
//            return "/mypage/remMC";
//         }
//   
//         
//         //회원탈퇴
//         @GetMapping("/remove")
//         public String removeMember(HttpServletRequest request, Model model, MembersDTO dto) {
//                      
//                 HttpSession session = request.getSession();
//               boolean SESS_AUTH = false;                
//               
//               try {
//                  SESS_AUTH = (boolean)session.getAttribute("SESS_AUTH");
//               }catch(Exception e) {}
//               
//               if( SESS_AUTH ) {        
//                  request.setAttribute("SESS_AUTH", false);
//                  String email = (String) session.getAttribute("SESS_EMAIL");
//
//                List<MembersDTO> mdto = service.getMypage(email);
//                model.addAttribute("membersDTO", mdto);                       
//                
//                service.removeMember(dto);
//                session.invalidate(); 
//                
//                return "redirect:/main/main";
//                
//               }else {
//                  return "redirect:/mypage/removeM"; 
//               }
//         
//      }           
         
//         //회원탈퇴 확인창
//         @GetMapping("/removecheck")
//         public String removeCheck(HttpServletRequest request, Model model, MembersDTO dto) {
//                      
//                 HttpSession session = request.getSession();
//               boolean SESS_AUTH = false;
//               
//               try {
//                  SESS_AUTH = (boolean)session.getAttribute("SESS_AUTH");
//               }catch(Exception e) {}
//               
//               if( SESS_AUTH ) {        
//                  request.setAttribute("SESS_AUTH", false);
//                  String email = (String) session.getAttribute("SESS_EMAIL");
//
//                List<MembersDTO> mdto = service.getMypage(email);
//                model.addAttribute("membersDTO", mdto);                       
//            
//         
//      }  
//            return "/mypage/removeM";    
//}
//         

         

   
}

   
// 