package com.spring.controller;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.domain.F_P_DTO;
import com.spring.domain.F_S_DTO;
import com.spring.domain.F_W_DTO;
import com.spring.service.F_P_Service;
import com.spring.service.F_S_Service;
import com.spring.service.F_W_Service;
import com.spring.util.Criteria;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * 각 메뉴의 즐겨찾기 리스트를 저장한 페이지와 이메일 전송을 처리하는 컨트롤러이다.
 * 
 * @author 김민주
 *
 */
@Controller
@RequestMapping("/fa/*")
@RequiredArgsConstructor
@Log4j
public class FavoriteController {
	private final F_S_Service services;
	private final F_W_Service servicew;
	private final F_P_Service servicep;
	
	/**
	 * 유기동물 공고 즐겨찾기 리스트를 페이징 처리하여 
	 * 
	 * @param session 
	 * @param nickname 
	 * @param model 
	 * @param cri 
	 * @return 로그인 정보가 null이 아니거나 로그인 되어있으면 유기동물 공고 jsp페이지, 그렇지 않으면 메인 jsp 페이지로 반환합니다.
	 */
	@RequestMapping("/favoritep")
	public String getPBoard(HttpSession session, String nickname, Model model, Criteria cri) {
		Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
		
		if (SESS_AUTH != null && SESS_AUTH) {
			nickname=(String) session.getAttribute("SESS_NICKNAME");
			int totalCount = servicep.getCountAllBoard(nickname);
			PageMaker pageMaker = new PageMaker(cri, totalCount);
			model.addAttribute("favoritep", servicep.getPBoardByPage(nickname, pageMaker));
			model.addAttribute("pageMaker", pageMaker);
			return "/fa/favoritep";
		} else {
			return "redirect:/main/main";
		}
	}
	
	@RequestMapping("/favorites")
	public String getSBoard(HttpSession session, Model model,
			Criteria cri, String nickname) {

		Boolean SESS_AUTH=(Boolean) session.getAttribute("SESS_AUTH");
		
		if (SESS_AUTH != null && SESS_AUTH) {
			nickname=(String) session.getAttribute("SESS_NICKNAME");
			int totalCount = services.getCountAllBoard(nickname);
			PageMaker pageMaker = new PageMaker(cri, totalCount);
			model.addAttribute("favorites", services.getSBoardByPage(nickname, pageMaker));
			model.addAttribute("pageMaker", pageMaker);
			return "/fa/favorites";
		} else {
			return "redirect:/main/main";
		}
	}

	@RequestMapping("/favoritew")
	public String getWBoard(HttpSession session, String nickname, Model model, Criteria cri) {
		Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
		
		if (SESS_AUTH != null && SESS_AUTH) {
			nickname=(String) session.getAttribute("SESS_NICKNAME");
			int totalCount = servicew.getCountAllBoard(nickname);
			PageMaker pageMaker = new PageMaker(cri, totalCount);
			model.addAttribute("favoritew", servicew.getWBoardByPage(nickname, pageMaker));
			model.addAttribute("pageMaker", pageMaker);
			return "/fa/favoritew";
		} else {
			return "redirect:/main/main";
		}
	}


	
	
	
	
	@GetMapping("/sendp")
	public String sendP() {
		return "/fa/sendfap";
	}
	@GetMapping("/sends")
	public String sendS() {
		return "/fa/sendfas";
	}
	@GetMapping("/sendw")
	public String sendW() {
		return "/fa/sendfaw";
	}
	
	
	@RequestMapping("/sendfap")
	public String naverMailSendP(HttpSession session2, String nickname, @RequestParam("email")String email
			,Model model) {
		 String host = "smtp.naver.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
	        String user = "2qiuo@naver.com"; // 패스워드
	        String password = "Yesol1101@";         

        // SMTP 서버 정보를 설정한다.
        Properties props = new Properties();
        
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.naver.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            nickname=(String) session2.getAttribute("SESS_NICKNAME");
            // 메일 제목
            message.setSubject("test 메일 송부");

            // 메일 내용을 HTML 형식으로 설정
            List<F_P_DTO> favoritepList = servicep.getPBoard(nickname);
            StringBuilder emailContent = new StringBuilder("<h1>유기동물 공고 즐겨찾기 목록</h1><br><ul>");
            for (F_P_DTO favItem : favoritepList) {
            	  emailContent.append(formatFavItemAsHtml(favItem));
            }
            emailContent.append("</ul>");

            // HTML을 포함한 메시지 내용 설정
            message.setContent(emailContent.toString(), "text/html; charset=UTF-8");

            // 메시지 전송
            Transport.send(message);
            log.info("success");
            model.addAttribute("msg", "success"); 
    		model.addAttribute("url", "sendp"); 
    		return "alert";	
        } catch (MessagingException e) {
        	model.addAttribute("msg", "fail"); 
    		model.addAttribute("url", "sendp"); 
            e.printStackTrace();
            return "alert";
        }
     
    }
	public String formatFavItemAsHtml(F_P_DTO favItem) {
	    return String.format("<li><strong>사진:</strong> <br><img src='%s' alt='이미지' width='300' height='200'>"
	    		+ "<br><strong>보호소 이름:</strong> %s <br><strong>보호소 주소:</strong> %s <br><strong>보호소 전화번호:</strong> %s <br> </li>",
	            favItem.getPopfile(), favItem.getCareNm(), favItem.getCareAddr(), favItem.getCareTel());
	}
	
	// 보호소 이메일 전송
	@RequestMapping("/sendfas")
	public String naverMailSendW(HttpSession session2, String nickname, @RequestParam("email")String email
			,Model model) {
		 String host = "smtp.naver.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
	        String user = "2qiuo@naver.com"; // 패스워드
	        String password = "Yesol1101@";     

        // SMTP 서버 정보를 설정한다.
        Properties props = new Properties();
        
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.naver.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            nickname=(String) session2.getAttribute("SESS_NICKNAME");
            // 메일 제목
            message.setSubject("test 메일 송부");

            // 메일 내용을 HTML 형식으로 설정
            List<F_S_DTO> favoritesList = services.getSBoard(nickname);
            StringBuilder emailContent = new StringBuilder("<h1>보호소 즐겨찾기 목록</h1><br><ul>");
            for (F_S_DTO favItem : favoritesList) {
            	  emailContent.append(formatFavItemAsHtml(favItem));
            }
            emailContent.append("</ul>");

            // HTML을 포함한 메시지 내용 설정
            message.setContent(emailContent.toString(), "text/html; charset=UTF-8");

            // 메시지 전송
            Transport.send(message);
            log.info("success");
            model.addAttribute("msg", "success"); 
    		model.addAttribute("url", "sends"); 
    		return "alert";	
        } catch (MessagingException e) {
        	model.addAttribute("msg", "fail"); 
    		model.addAttribute("url", "sends"); 
            e.printStackTrace();
            return "alert";
        }
     
    }
	public String formatFavItemAsHtml(F_S_DTO favItem) {
	    return String.format("<li><br><strong>보호소 이름:</strong> %s <br><strong>보호소 주소:</strong> %s <br><strong>보호소 전화번호:</strong> %s <br> </li>",
	             favItem.getCareNm(), favItem.getCareAddr(), favItem.getCareTel());
	}
	
	@RequestMapping("/sendfaw")
	public String naverMailSend(HttpSession session2, String nickname, @RequestParam("email")String email
			,Model model) {
		 String host = "smtp.naver.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
	        String user = "2qiuo@naver.com"; // 패스워드
	        String password = "Yesol1101@";        

        // SMTP 서버 정보를 설정한다.
        Properties props = new Properties();
        
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.naver.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            nickname=(String) session2.getAttribute("SESS_NICKNAME");
            // 메일 제목
            message.setSubject("test 메일 송부");

            // 메일 내용을 HTML 형식으로 설정
            List<F_W_DTO> favoritewList = servicew.getWBoard(nickname);
            StringBuilder emailContent = new StringBuilder("<h1>위드펫 즐겨찾기 목록</h1><br><ul>");
            for (F_W_DTO favItem : favoritewList) {
            	  emailContent.append(formatFavItemAsHtml(favItem));
            }
            emailContent.append("</ul>");

            // HTML을 포함한 메시지 내용 설정
            message.setContent(emailContent.toString(), "text/html; charset=UTF-8");

            // 메시지 전송
            Transport.send(message);
            log.info("success");
            model.addAttribute("msg", "success"); 
    		model.addAttribute("url", "sendw"); 
    		return "alert";	
        } catch (MessagingException e) {
        	model.addAttribute("msg", "fail"); 
    		model.addAttribute("url", "sendw"); 
            e.printStackTrace();
            return "alert";
        }
     
    }
	public String formatFavItemAsHtml(F_W_DTO favItem) {
	    return String.format("<li><br><strong>건물 이름:</strong> %s <br><strong>건물 주소:</strong> %s <br><strong>건물 전화번호:</strong> %s <br> </li>",
	             favItem.getBuilding(), favItem.getRoad(), favItem.getTel());
	}
}
