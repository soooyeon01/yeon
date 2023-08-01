package com.spring.controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
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
import com.spring.util.SendEmail; 

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
		} 
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
	
	@ResponseBody
	@PostMapping("/sendAuthNum")
	public String sendAuthNum(@RequestParam("email") String email, HttpSession session) {		
	  String authNum = getRandomAuthNum(); // 인증코드 생성
	  session.setAttribute("authNum", authNum); // 세션에 인증 코드 저장

	  String subject = "옥독캣 회원가입 이메일 인증번호입니다.";
	  String text = "회원가입 인증번호는 " + authNum + " 입니다.";
	  
	  SendEmail.naverMailSend(email, subject, text); // 이메일 발송
	  
	  return "success";
	}
	
	@ResponseBody
	@PostMapping("/checkAuthNum")
	public boolean checkAuthNum(@RequestParam("inputNum") String inputNum, HttpSession session) {
	  String authNum = (String)session.getAttribute("authNum"); // 세션에서 인증 코드 가져오기
	  
	  if (inputNum.equals(authNum)) { // 입력한 코드와 저장된 코드 비교
	      return true; // 일치한다면 인증 성공
	  } else {
	      return false; // 일치하지 않는다면 인증 실패
	  }
	}
	
	private String getRandomAuthNum() {
		  Random random = new Random();
		  int authNum = random.nextInt(899999) + 100000; // 100000 ~ 999999 사이의 랜덤한 수 생성
		  return String.valueOf(authNum);
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
			model.addAttribute("url", "login"); 
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
	
	
	@PostMapping ("/findPwd") 
	public String findPwd(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("phone") int phone, Model model) throws IOException{
		MembersDTO mdto = new MembersDTO();
		
		mdto.setName(name);
		mdto.setEmail(email);
		mdto.setPhone(phone);
		
	    int count = servicep.findPwd(mdto);
		
		if (count > 0) {
	        String tempPwd = servicep.makeTempPwd();
	        mdto.setTempPwd(tempPwd);
	        servicep.updatePwd(mdto);
	        
	        String subject = "임시 비밀번호 발급 안내";
	        String text = "안녕하세요. 회원님의 임시 비밀번호는 " + tempPwd + " 입니다. 로그인 후 반드시 비밀번호를 변경해주세요.";
	        SendEmail.naverMailSend(email, subject, text);
	        
	        model.addAttribute("msg", "이메일로 임시 비밀번호를 발송하였습니다.");
			model.addAttribute("url", "login"); 
	        return "alert";
	    } else {
	        model.addAttribute("msg", "없는 정보입니다");
	        return "alert";
	    }
	}
	
	
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/main/main";
	}
}
