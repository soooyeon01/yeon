package com.spring.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	private final JoinService servicej;
	private final FindEmailService servicee;
	private final FindPwdService servicep;
	
	@GetMapping("/login")
	public String loginget(MembersDTO mdto) {
		return "user/login";
	}
	
	@PostMapping("/login")
	public String loginPost(@RequestParam("email") String email,
	                        @RequestParam("pwd") String password,
	                        HttpSession session,
	                        Model model) {
	    MembersDTO mdto = new MembersDTO();
	    mdto.setEmail(email);
	    mdto.setPwd(password);
	    
	    if(service.countLogin(mdto) == 1) {
	    	service.selectLogin(mdto);
	        session.setAttribute("SESS_AUTH", true);
	        session.setAttribute("SESS_EMAIL", mdto.getEmail());
	        return "redirect:/main/main";
	    } else {
	        model.addAttribute("msg", "로그인 실패되었습니다");
	        model.addAttribute("url", "login");
	        return "/alert";    		
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
			model.addAttribute("msg", "회원가입 완료되었습니다");
	        model.addAttribute("url", "login");
	        return "/alert";
		}else {
			model.addAttribute("msg", "회원가입 실패하였습니다");
			model.addAttribute("url", "join");
	        return "/alert";
		} //포기한다
		
	}

	@GetMapping("/findEmail")
	public String findEmailget(MembersDTO mdto) {
		return "user/findEmail";
	}
	@PostMapping("/findEmail")
	public String findEmail(@RequestParam("name") String name,@RequestParam("phone") String phone,Model model) {
		MembersDTO mdto = new MembersDTO();

		String email = servicee.findEmail(mdto);
		
		if (email != null) {
			  model.addAttribute("msg", "회원님의 이메일은 " + email + "입니다.");
		} else {
			  model.addAttribute("msg", "없는 정보입니다.");
		}
	
		return "user/findEmail";
	}
	
	
	@GetMapping("/findPwd")
	public String findPwd(MembersDTO mdto) {
		servicep.findPwd(mdto);
		return "user/findEmail";
	}
	
}
