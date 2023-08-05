package com.spring.controller;

import java.util.List;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
 * 각기 다른 메뉴의 즐겨찾기 리스트를 관리하고 저장한 페이지와 이메일 발송을 처리하는 컨트롤러 클래스 입니다.
 * 
 * @author 김민주
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
	 * 페이징된 유기동물 공고의 즐겨찾기 목록을 가져와 모델에 추가합니다.
	 * 
	 * @param session  - HttpSession의 객체
	 * @param nickname - 사용자의 닉네임
	 * @param model    - 속성을 추가하기 위한 Model 객체
	 * @param cri      - 페이징을 위한 Criteria 객체
	 * @see com.spring.service.F_P_Service#getPBoardByPage(String, PageMaker) - 닉네임을
	 *      기준으로 페이징 된 유기동물 공고 즐겨찾기 목록 조회
	 * @see com.spring.service.F_P_Service#getCountAllBoard(String) - 닉네임을 기준으로 유기동물
	 *      공고 즐겨찾기 목록 전체 개수 조회
	 * @return 로그인 되어있으면 유기동물 공고 jsp페이지를 반환, 그렇지 않으면 메인 jsp 페이지로 redirect 합니다.
	 */
	@RequestMapping("/favoritep")
	public String getPBoard(HttpSession session, String nickname, Model model, Criteria cri) {
		Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
		if (SESS_AUTH != null && SESS_AUTH) {
			nickname = (String) session.getAttribute("SESS_NICKNAME");
			int totalCount = servicep.getCountAllBoard(nickname);
			PageMaker pageMaker = new PageMaker(cri, totalCount);
			model.addAttribute("favoritep", servicep.getPBoardByPage(nickname, pageMaker));
			model.addAttribute("pageMaker", pageMaker);
			return "/fa/favoritep";
		} else {
			return "redirect:/main/main";
		}
	}

	/**
	 * 페이징된 보호소의 즐겨찾기 목록을 가져와 모델에 추가합니다.
	 * 
	 * @param session  - HttpSession의 객체
	 * @param nickname - 사용자의 닉네임
	 * @param model    - 속성을 추가하기 위한 Model 객체
	 * @param cri      - 페이징을 위한 Criteria 객체
	 * @see com.spring.service.F_S_Service#getSBoardByPage(String, PageMaker) - 닉네임을
	 *      기준으로 페이징 된 보호소 즐겨찾기 목록 조회
	 * @see com.spring.service.F_S_Service#getCountAllBoard(String) - 닉네임을 기준으로 보호소
	 *      즐겨찾기 목록 전체 개수 조회
	 * @return 로그인 되어있으면 보호소 jsp페이지를 반환, 그렇지 않으면 메인 jsp 페이지로 redirect 합니다.
	 */
	@RequestMapping("/favorites")
	public String getSBoard(HttpSession session, String nickname, Model model, Criteria cri) {
		Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
		if (SESS_AUTH != null && SESS_AUTH) {
			nickname = (String) session.getAttribute("SESS_NICKNAME");
			int totalCount = services.getCountAllBoard(nickname);
			PageMaker pageMaker = new PageMaker(cri, totalCount);
			model.addAttribute("favorites", services.getSBoardByPage(nickname, pageMaker));
			model.addAttribute("pageMaker", pageMaker);
			return "/fa/favorites";
		} else {
			return "redirect:/main/main";
		}
	}

	/**
	 * 페이징된 반려동물 문화시설의 즐겨찾기 목록을 가져와 모델에 추가합니다.
	 * 
	 * @param session  - HttpSession의 객체
	 * @param nickname - 사용자의 닉네임
	 * @param model    - 속성을 추가하기 위한 Model 객체
	 * @param cri      - 페이징을 위한 Criteria 객체
	 * @see com.spring.service.F_W_Service#getWBoardByPage(String, PageMaker) - 닉네임을
	 *      기준으로 페이징 된 반려동물 문화시설 즐겨찾기 목록 조회
	 * @see com.spring.service.F_W_Service#getCountAllBoard(String) - 닉네임을 기준으로 반려동물
	 *      문화시설 즐겨찾기 목록 전체 개수 조회
	 * @return 로그인 되어있으면 반려동물 문화시설 jsp페이지를 반환, 그렇지 않으면 메인 jsp 페이지로 redirect 합니다.
	 */
	@RequestMapping("/favoritew")
	public String getWBoard(HttpSession session, String nickname, Model model, Criteria cri) {
		Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
		if (SESS_AUTH != null && SESS_AUTH) {
			nickname = (String) session.getAttribute("SESS_NICKNAME");
			int totalCount = servicew.getCountAllBoard(nickname);
			PageMaker pageMaker = new PageMaker(cri, totalCount);
			model.addAttribute("favoritew", servicew.getWBoardByPage(nickname, pageMaker));
			model.addAttribute("pageMaker", pageMaker);
			return "/fa/favoritew";
		} else {
			return "redirect:/main/main";
		}
	}

	/**
	 * 사용자 이메일(로그인 할 때 id)로 유기동물 공고 즐겨찾기 목록을 보냅니다.
	 * 
	 * @param session2 - HttpSession 객체
	 * @param nickname - 사용자의 닉네임
	 * @param email    - 사용자의 이메일 주소
	 * @param model    - 속성을 추가하기 위한 Model 객체
	 * @see com.spring.service.F_P_Service#getPBoard(String) - 닉네임을 기준으로 유기동물 공고
	 *      즐겨찾기 목록 조회
	 * @return 이메일 발송 성공 또는 실패에 따라 적절한 경고 메세지 띄우고 유기동물 공고 즐겨찾기 페이지로 가는 alert jsp 반환
	 */
	@RequestMapping("/sendfap")
	public String naverMailSendP(HttpSession session2, String nickname, String email, Model model) {
		String host = "smtp.naver.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
		String user = "1qaietx@naver.com"; // 패스워드
        String password = "minjaes2";

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
			nickname = (String) session2.getAttribute("SESS_NICKNAME");
			email = (String) session2.getAttribute("SESS_EMAIL");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			// 메일 제목
			message.setSubject("유기동물 즐겨찾기 목록");

			// 메일 내용을 HTML 형식으로 설정
			List<F_P_DTO> favoritepList = servicep.getPBoard(nickname);
			if (favoritepList.isEmpty()) {
				model.addAttribute("msg", "이메일 전송 실패: 즐겨찾기 목록이 비어 있습니다.");
				model.addAttribute("url", "/4jojo/fa/favoritep");
				return "alert";
			} else {
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
				model.addAttribute("msg", "이메일 전송 성공");
				model.addAttribute("url", "/4jojo/fa/favoritep");
				return "alert";
			}
		} catch (MessagingException e) {
			model.addAttribute("msg", "이메일 전송 실패");
			model.addAttribute("url", "/4jojo/fa/favoritep");
			e.printStackTrace();
			return "alert";
		}

	}

	/**
	 * F_P_DTO(유기동물 공고 즐겨찾기 DTO)객체의 정보를 HTML 형식으로 변환하여 반환한다.
	 *
	 * @param favItem - F_P_DTO 객체
	 * @return favItem의 정보를 포함한 HTML 문자열
	 */
	public String formatFavItemAsHtml(F_P_DTO favItem) {
		return String.format("<li><strong>사진:</strong> <br><img src='%s' alt='이미지' width='300' height='200'>"
				+ "<br><strong>보호소 이름:</strong> %s <br><strong>보호소 주소:</strong> %s <br><strong>보호소 전화번호:</strong> %s <br> </li>",
				favItem.getPopfile(), favItem.getCareNm(), favItem.getCareAddr(), favItem.getCareTel());
	}

	/**
	 * 사용자 이메일(로그인 할 때 id)로 보호소 즐겨찾기 목록을 보냅니다.
	 * 
	 * @param session2 - HttpSession 객체
	 * @param nickname - 사용자의 닉네임
	 * @param email    - 사용자의 이메일 주소
	 * @param model    - 속성을 추가하기 위한 Model 객체
	 * @see com.spring.service.F_S_Service#getSBoard(String) - 닉네임을 기준으로 보호소 즐겨찾기 목록
	 *      조회
	 * @return 이메일 발송 성공 또는 실패에 따라 적절한 경고 메세지 띄우고 보호소 즐겨찾기 페이지로 가는 alert jsp 반환
	 */
	@RequestMapping("/sendfas")
	public String naverMailSendW(HttpSession session2, String nickname, String email, Model model) {
		String host = "smtp.naver.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
		String user = "1qaietx@naver.com"; // 패스워드
        String password = "minjaes2";

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
			nickname = (String) session2.getAttribute("SESS_NICKNAME");
			email = (String) session2.getAttribute("SESS_EMAIL");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			// 메일 제목
			message.setSubject("보호소 즐겨찾기 목록");

			// 메일 내용을 HTML 형식으로 설정
			List<F_S_DTO> favoritesList = services.getSBoard(nickname);
			if (favoritesList.isEmpty()) {
				model.addAttribute("msg", "이메일 전송 실패: 즐겨찾기 목록이 비어 있습니다.");
				model.addAttribute("url", "/4jojo/fa/favorites");
				return "alert";
			} else {
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
				model.addAttribute("msg", "이메일 전송 성공");
				model.addAttribute("url", "/4jojo/fa/favorites");
				return "alert";
			}
		} catch (MessagingException e) {
			model.addAttribute("msg", "이메일 전송 실패");
			model.addAttribute("url", "/4jojo/fa/favorites");
			e.printStackTrace();
			return "alert";
		}

	}

	/**
	 * F_S_DTO(보호소 즐겨찾기 DTO)객체의 정보를 HTML 형식으로 변환하여 반환한다.
	 *
	 * @param favItem - F_S_DTO 객체
	 * @return favItem의 정보를 포함한 HTML 문자열
	 */
	public String formatFavItemAsHtml(F_S_DTO favItem) {
		return String.format(
				"<li><br><strong>보호소 이름:</strong> %s <br><strong>보호소 주소:</strong> %s <br><strong>보호소 전화번호:</strong> %s <br> </li>",
				favItem.getCareNm(), favItem.getCareAddr(), favItem.getCareTel());
	}

	/**
	 * 사용자 이메일(로그인 할 때 id)로 반려동물 문화시설 즐겨찾기 목록을 보냅니다.
	 * 
	 * @param session2 - HttpSession 객체
	 * @param nickname - 사용자의 닉네임
	 * @param email    - 사용자의 이메일 주소
	 * @param model    - 속성을 추가하기 위한 Model 객체
	 * @see com.spring.service.F_W_Service#getWBoard(String) - 닉네임을 기준으로 반려동물 문화시설
	 *      즐겨찾기 목록 조회
	 * @return 이메일 발송 성공 또는 실패에 따라 적절한 경고 메세지 띄우고 반려동물 문화시설 즐겨찾기 페이지로 가는 alert jsp
	 *         반환
	 */
	@RequestMapping("/sendfaw")
	public String naverMailSend(HttpSession session2, String nickname, String email, Model model) {
		String host = "smtp.naver.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
		String user = "1qaietx@naver.com"; // 패스워드
        String password = "minjaes2";

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
			nickname = (String) session2.getAttribute("SESS_NICKNAME");
			email = (String) session2.getAttribute("SESS_EMAIL");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			// 메일 제목
			message.setSubject("위드펫 즐겨찾기 목록");

			// 메일 내용을 HTML 형식으로 설정
			List<F_W_DTO> favoritewList = servicew.getWBoard(nickname);
			if (favoritewList.isEmpty()) {
				model.addAttribute("msg", "이메일 전송 실패: 즐겨찾기 목록이 비어 있습니다.");
				model.addAttribute("url", "/4jojo/fa/favoritew");
				return "alert";
			} else {
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
				model.addAttribute("msg", "이메일 전송 성공");
				model.addAttribute("url", "/4jojo/fa/favoritew");
				return "alert";
			}
		} catch (MessagingException e) {

			model.addAttribute("msg", "이메일 전송 실패");
			model.addAttribute("url", "/4jojo/fa/favoritew");
			e.printStackTrace();
			return "alert";

		}

	}

	/**
	 * F_W_DTO(반려동물 문화시설 즐겨찾기 DTO)객체의 정보를 HTML 형식으로 변환하여 반환한다.
	 *
	 * @param favItem - F_W_DTO 객체
	 * @return favItem의 정보를 포함한 HTML 문자열
	 */
	public String formatFavItemAsHtml(F_W_DTO favItem) {
		return String.format(
				"<li><br><strong>건물 이름:</strong> %s <br><strong>건물 주소:</strong> %s <br><strong>건물 전화번호:</strong> %s <br> </li>",
				favItem.getBuilding(), favItem.getRoad(), favItem.getTel());
	}
}
