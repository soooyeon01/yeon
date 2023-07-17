package com.spring.controller;

import java.io.IOException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.domain.MembersDTO;
import com.spring.service.FindEmailService;
import com.spring.service.FindPwdService;
import com.spring.service.JoinService;
import com.spring.service.LoginService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class UserController {
   @Autowired
   private final LoginService service;
   @Autowired
   private final JoinService servicej;
   @Autowired
   private final FindEmailService servicee;
   @Autowired
   private final FindPwdService servicep;
   
   @GetMapping("/login")
   public String loginget(MembersDTO mdto) {
      return "user/login";
   }
   
   @PostMapping("/login")
   public String loginPost(@RequestParam("email") String email,@RequestParam("pwd") String password,
        HttpSession session,Model model,MembersDTO mdto) {
       //MembersDTO mdto = new MembersDTO();
       mdto.setEmail(email);
       mdto.setPwd(password);
       
       if(service.countLogin(mdto) == 1) {
          mdto=service.selectLogin(mdto);
          
           session.setAttribute("SESS_AUTH", true);
           session.setAttribute("SESS_EMAIL", mdto.getEmail());
           session.setAttribute("SESS_NICKNAME", mdto.getNickname());
           
           return "redirect:/main/main";
       } else {
         model.addAttribute("msg", "로그인 실패");   
         return "alert";
       }
   }
		
   @GetMapping("/join")
	public String joinget(MembersDTO mdto) {
		return "user/join";
	}
	
	@PostMapping("/join")
	public String joinpost(MembersDTO mdto, Model model) {
		int isOk = 1;
		if( servicej.registerMembers(mdto) == isOk) {
			model.addAttribute("msg", "회원가입 완료"); 
			model.addAttribute("url", "login"); 
			return "alert";
			
		} else {
			model.addAttribute("msg", "회원가입 실패"); 
			return "alert";
		} //회원가입 실패 안 됨 중복 들어가서 안 되는 듯 중복 확인 ㄱㄱ 
	}
	
	@ResponseBody
	@PostMapping("/emailCheck")
	public int emailCheck(@RequestParam("email") String email) {
		int cnt = servicej.emailCheck(email);
		return cnt;
	}
	
	@ResponseBody
	@PostMapping("/nicknameCheck")
	public int nicknameCheck(@RequestParam("nickname") String nickname) {
		int cnt = servicej.nicknameCheck(nickname);
		return cnt;
	}
	
	@ResponseBody
	@PostMapping("/phoneCheck")
	public int phoneCheck(@RequestParam("phone") int phone) {
		int cnt = servicej.phoneCheck(phone);
		return cnt;
	}

	@GetMapping("/findEmail")
	public String findEmailget(MembersDTO mdto) {
		return "user/findEmail";
	}
	
	@PostMapping("/findEmail")
	public String findEmail(@RequestParam("name") String name, @RequestParam("phone") int phone, Model model) throws IOException{
    	MembersDTO mdto = new MembersDTO();
		
		mdto.setName(name);
		mdto.setPhone(phone);
		
		String email = servicee.findEmail(mdto);
		
		if (email != null) {
			model.addAttribute("msg", "회원님의 이메일은 " + email + " 입니다"); 
			 return "alert";
			
		} else {
			model.addAttribute("msg", "없는 정보입니다");	
			 return "alert";
		}
	}
	
	
	@GetMapping("/findPwd")
	public String findPwdget(MembersDTO mdto) {
		return "user/findPwd";
	}
	
	@PostMapping
	public String findPwd(MembersDTO mdto) {
		servicep.findPwd(mdto);
		return "user/findPwd";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/main/main";
	}
}
