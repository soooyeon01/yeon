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
import com.spring.util.SHAEncodeUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/mypage/*")//
@RequiredArgsConstructor
@Log4j
public class MypageController {
   
   @Autowired
   private MypageService service;

   @RequestMapping("/mypage")
   public String showMypage(HttpSession session, Model model) {
       Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");

       if (SESS_AUTH != null && SESS_AUTH) {
           String email = (String) session.getAttribute("SESS_EMAIL");
           List<MembersDTO> mdto = service.getMypage(email);
           model.addAttribute("membersDTO", mdto);
           return "/mypage/mypage";
       } else {
           return "redirect:/main/main";
       }
   }   
   
	// pwd변경
	@PostMapping("/upmypwd")
	public String upmypwd(HttpSession session, Model model, MembersDTO dto, @RequestParam("pwd") String pwd,
			@RequestParam("newpwd") String newpwd, @RequestParam("newcpwd") String newcpwd) {

		Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH"); // 세션에서 인증여부 가져옴
		String email = (String) session.getAttribute("SESS_EMAIL"); // 세션에서 이메일 가져옴
		List<MembersDTO> mdto = service.getMypage(email); // 사용자 정보를 가져옴
		model.addAttribute("membersDTO", mdto); // 사용자 정보를 뷰에 전달하기 위해 모델에 추가

		String nowpwd = service.getPwd(email); // 현재 비밀번호

		if (SESS_AUTH != null && SESS_AUTH) {// 세션 인증이 된 경우

			if (pwd == null || pwd == "" || newpwd == null || newpwd == "" || newcpwd == null || newcpwd == "") { // 입력값이
																													// 비어있는지
																													// 확인
				model.addAttribute("msg", "입력이 완료되지 않았습니다.");
				model.addAttribute("url", "upmypage");
				return "alert"; // 입력이 누락된 경우 오류 메시지 표시
			}

			if (newpwd.length() < 8) { // 새 비밀번호 길이 확인(8자 이상)
				model.addAttribute("msg", "새 비밀번호 8자 이상 적어주세요.");
				model.addAttribute("url", "upmypage");
				return "alert"; // 새 비밀번호 길이가 부족한 경우 경고 메시지를 표시
			} else {
				String encodedPwd = SHAEncodeUtil.encodeSha(pwd); // 입력한 비밀번호 암호화

				if (encodedPwd.equals(nowpwd) && !newpwd.equals(newcpwd)) {       // 입력한 비밀번호가 현재 비밀번호와 일치하고
					model.addAttribute("msg", "입력하신 새 비밀번호가 일치하지 않습니다."); // ㄴ새 비밀번호와 확인 비밀번호가 일치하지 않은 경우
					model.addAttribute("url", "upmypage");
					return "alert";

				} else if (!encodedPwd.equals(nowpwd) && newpwd.equals(newcpwd)) {// 입력한 비밀번호가 현재 비밀번호와 불일치하고
					model.addAttribute("msg", "현재 비밀번호가 일치하지 않습니다."); //    ㄴ확인 비밀번호가 일치하는 경우
					model.addAttribute("url", "upmypage");
					return "alert";

				} else if (encodedPwd.equals(nowpwd) && newpwd.equals(newcpwd)) {   // 입력한 비밀번호가 현재 비밀번호와 일치하고
																					// ㄴ새 비밀번호와 확인 비밀번호가 일치하는 경우
					String encodedNewPwd = SHAEncodeUtil.encodeSha(newpwd); //새 비밀번호를 암호화해서 MembersDTO를 수정
					dto.setPwd(encodedNewPwd);// 새로 암호화된 비밀번호를 DTO에 저장
					service.modifyPwd(dto);// 비밀번호 변경 작업 수행
					model.addAttribute("msg", "비밀번호 변경이 완료되었습니다.");
					model.addAttribute("url", "upmypage");
					return "alert";// 변경 완료 메시지 표시
				}
			}
		} else {// 세션 인증이 되지 않은 경우
			return "redirect:/main/main"; // 메인 페이지로 리다이렉트
		}
		return null;// 모든 분기 상황에서 return문이 있기 때문에 이부분은 실행되지 않음.
	}
   
	@PostMapping("/upmyphone")
	public String upmyphone(HttpSession session, Model model, MembersDTO dto, @RequestParam("phone") String phone) {
	    Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
	    if (SESS_AUTH != null && SESS_AUTH) { // 세션 인증이 된 경우
	    	
	        String email = (String) session.getAttribute("SESS_EMAIL"); // 세션에서 이메일을 가져옴
	        List<MembersDTO> mdtoList = service.getMypage(email);// 사용자 정보 가져옴
	        model.addAttribute("membersDTO", mdtoList);// 사용자 정보를 뷰에 전달하기 위해 모델에 추가
	        List<MembersDTO> phonecheck = service.getPhoneC(); //전화번호 중복 체크를 위한 membersList 가져옴
	        boolean phoneExists = false; // 전화번호 존재 여부 확인 변수 선언

	        try {
	            int phone2 = Integer.parseInt(phone); // 전화번호를 (int)로 변환
	            for (MembersDTO pdto : phonecheck) { //phonecheck 목록에 있는 모든 MembersDTO 객체를 순회하며 확인
	                if (pdto.getPhone() == phone2) { //현재 순회 중인 객체의 전화번호와 입력된 전화번호가 동일한지 확인
	                    phoneExists = true; // 전화번호가 이미 존재하면 phoneExists를 true로 설정
	                    break;
	                }
	            }

	            if (phoneExists) {
	                model.addAttribute("msg", "이미 존재하는 전화번호입니다.");
	                model.addAttribute("url", "upmypage");
	                return "alert";
	            } else {
	                dto.setPhone(phone2); // 변환된 숫자 DTO에 저장
	                service.modifyPhone(dto); // 전화번호 변경 작업 수행
	                model.addAttribute("msg", "전화번호 변경이 완료되었습니다.");
	                model.addAttribute("url", "upmypage");
	                return "alert"; // 변경 완료 메시지 표시
	            }
	        } catch (NumberFormatException e) { // 전화번호 입력값이 int로 변환할 수 없는 경우 로그출력
	            e.printStackTrace();
	        }

	    } else { // 세션 인증이 되지 않은 경우 main으로 돌아감.
	        return "redirect:/main/main";
	    }
	    return null; // 컴파일 에러 방지용
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
	        	  
	        	// inputpwd를 같은 암호화 방법으로 인코딩
	              String encodedInputPwd = SHAEncodeUtil.encodeSha(inputpwd);
	        	  
		              if (pwd.equals(encodedInputPwd)) {
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
	        	  
	        	// inputpwd를 같은 암호화 방법으로 인코딩
	              String encodedInputPwd = SHAEncodeUtil.encodeSha(inputpwd);	              
	              
		              if (pwd.equals(encodedInputPwd)) {
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
	      
