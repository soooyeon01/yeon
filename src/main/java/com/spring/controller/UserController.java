package com.spring.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.spring.domain.F_W_DTO;
import com.spring.domain.LikeDTO;
import com.spring.domain.MembersDTO;
import com.spring.domain.ReplyDTO;
import com.spring.domain.W_DTO;
import com.spring.service.FindEmailService;
import com.spring.service.FindPwdService;
import com.spring.service.JoinService;
import com.spring.service.LoginService;
import com.spring.service.MembersService;
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
   @Autowired
   private final MembersService mservice;
  
   
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
	public String sendAuthNum(@RequestParam("email") String email, HttpSession session, Model model) {
	    String authNum = servicep.makeTempPwd();  // 임시 인증번호 생성
	    session.setAttribute("authNum", authNum);  // 세션에 인증번호 저장

	    String subject = "옥독캣 이메일 인증번호 발송";
	    String text = "안녕하세요. 회원님의 이메일 인증번호는 " + authNum + " 입니다.";
	    SendEmail.naverMailSend(email, subject, text);

	    return "이메일로 인증번호를 발송하였습니다.";
	}

	@RequestMapping("/checkAuthNum")
	public String checkAuthNum(@RequestParam("inputNum") String inputNum, HttpSession session, Model model) {
	    String sessionAuthNum = (String) session.getAttribute("authNum");
	    if (sessionAuthNum != null && inputNum.equals(sessionAuthNum)) {
	        session.removeAttribute("authNum");  // 인증 완료 시 세션에서 인증번호 정보 삭제
	        model.addAttribute("authResult", true); // 모델 객체에 인증 결과 저장
	    } else {
	        model.addAttribute("authResult", false); // 모델 객체에 인증 결과 저장
	    }
	    return "resultView"; // 모델 객체를 처리할 View의 이름 반환
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
	
	//-------------------------------
	
	//@RequestMapping("/userlist")
//	@RequestMapping(value = "/userlist", produces = "application/json")
//	@ResponseBody
//	public Map<String, Object> MemberList(HttpSession session, Model model) {
//
//		Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
//		List<MembersDTO> mlist=mservice.getMemberList();
//		int mtotal=mservice.memberCnt();
//		Map<String, Object> map = new HashMap<>();
//        
//        if(SESS_AUTH != null && SESS_AUTH) {
////          request.setCharacterEncoding("utf-8");
//            String email = (String) session.getAttribute("SESS_EMAIL");
//            String nickname = (String) session.getAttribute("SESS_NICKNAME");
//            model.addAttribute("userList", mservice.getMemberList());
//            log.info("회원리스트는 "+mlist);
//            map.put("mlist", mlist);
//            map.put("mtotal", mtotal);
//            return map;
//        }else {
//			return map;
//        }
//
//	}
	
	@RequestMapping("/userlist")
	public String CommunityList(HttpSession session, Model model, MembersDTO mdto) {

		Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
         
        if(SESS_AUTH != null && SESS_AUTH) {
//          request.setCharacterEncoding("utf-8");
            String email = (String) session.getAttribute("SESS_EMAIL");
            String nickname = (String) session.getAttribute("SESS_NICKNAME");
            model.addAttribute("userList", mservice.getMemberList());

            //model.addAttribute("likeCnt", lservice.getLikeCnt(ldto));
            return "user/userlist";
        }else {
			return "redirect:/main/main";
        }
        
	}
	
//	@PostMapping("/kick")
//	@ResponseBody		
//	public Map<String, String> DeleteU(@RequestBody MembersDTO mdto, HttpSession session, @RequestParam("userEmail") String userEmail) {
//		log.info("픽 "+userEmail);
//		Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
//        System.out.println("회원 삭제 통신 성공");
// 
//        Map<String, String> map = new HashMap<>(); 
//        if(SESS_AUTH != null && SESS_AUTH) {
////          request.setCharacterEncoding("utf-8");
//            String Logemail = (String) session.getAttribute("SESS_EMAIL");
//            String nickname = (String) session.getAttribute("SESS_NICKNAME");
//            
//            log.info("로그인 유지중...");
//			mservice.kick(userEmail);
//			
//			log.info("회원 삭제 서비스 성공");
//			
//			map.put("result", "kickSuccess");
//			
//        } else {
//        	map.put("result", "fail");
//		}
//        return map;
//	}
	@PostMapping("/kick")
	@ResponseBody
	public String delete_W(HttpSession session, @RequestParam("userEmail") String userEmail)throws ServletException, IOException {
		log.info("df"+userEmail);
		Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
		MembersDTO dto = new MembersDTO();
	
		dto.setEmail(userEmail);
	    
	   
	    if(SESS_AUTH != null && SESS_AUTH) {
		int result = mservice.kick(userEmail);
		// { result : 1}

		JsonObject jsonObj = new JsonObject();
	    jsonObj.addProperty("result", result);

	    return jsonObj.toString();
	    }else {
		return "redirect:main/main";
	    }
	 }
	    
	
}

