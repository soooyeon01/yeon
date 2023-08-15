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
import com.spring.domain.FavoritePetDTO;
import com.spring.domain.FavoriteShelDTO;
import com.spring.domain.FavoriteWithDTO;
import com.spring.service.FavoritePetService;
import com.spring.service.FavoriteShelService;
import com.spring.service.FavoriteWithService;
import com.spring.util.Criteria;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/fa/*")
@RequiredArgsConstructor
@Log4j
public class FavoriteController {
	private final FavoriteShelService services;
	private final FavoriteWithService servicew;
	private final FavoritePetService servicep;

	@RequestMapping("/favoritep")
	public String getPBoard(HttpSession session, String nickname, Model model, Criteria cri) {
		Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
		if (SESS_AUTH != null && SESS_AUTH) {
			nickname = (String) session.getAttribute("SESS_NICKNAME");
			int totalCount = servicep.getLikedPetCount(nickname);
			PageMaker pageMaker = new PageMaker(cri, totalCount);
			model.addAttribute("favoritep", servicep.getLikedPetList(nickname, pageMaker));
			model.addAttribute("pageMaker", pageMaker);
			return "/fa/favoritep";
		} else {
			return "redirect:/main/main";
		}
	}

	@RequestMapping("/favorites")
	public String getSBoard(HttpSession session, String nickname, Model model, Criteria cri) {
		Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
		if (SESS_AUTH != null && SESS_AUTH) {
			nickname = (String) session.getAttribute("SESS_NICKNAME");
			int totalCount = services.getLikedShelCount(nickname);
			PageMaker pageMaker = new PageMaker(cri, totalCount);
			model.addAttribute("favorites", services.getLikedShelList(nickname, pageMaker));
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
			nickname = (String) session.getAttribute("SESS_NICKNAME");
			int totalCount = servicew.getLikedWithCount(nickname);
			PageMaker pageMaker = new PageMaker(cri, totalCount);
			model.addAttribute("favoritew", servicew.getLikedWithList(nickname, pageMaker));
			model.addAttribute("pageMaker", pageMaker);
			return "/fa/favoritew";
		} else {
			return "redirect:/main/main";
		}
	}

	@RequestMapping("/sendfap")
	public String naverMailSendP(HttpSession session2, String nickname, String email, Model model) {
		String host = "smtp.naver.com";
		String user = "ptiael@naver.com";
		String password = "yesol1101s2";

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
			message.setSubject("유기동물 즐겨찾기 목록");

			List<FavoritePetDTO> favoritepList = servicep.getLikedPetData(nickname);
			if (favoritepList.isEmpty()) {
				model.addAttribute("msg", "이메일 전송 실패: 즐겨찾기 목록이 비어 있습니다.");
				model.addAttribute("url", "/4jojo/fa/favoritep");
				return "alert";
			} else {
				StringBuilder emailContent = new StringBuilder("<h1>유기동물 공고 즐겨찾기 목록</h1><br><ul>");
				for (FavoritePetDTO favItem : favoritepList) {
					emailContent.append(formatFavItemAsHtml(favItem));
				}
				emailContent.append("</ul>");
				message.setContent(emailContent.toString(), "text/html; charset=UTF-8");
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

	public String formatFavItemAsHtml(FavoritePetDTO favItem) {
		return String.format("<li><strong>사진:</strong> <br><img src='%s' alt='이미지' width='300' height='200'>"
				+ "<br><strong>보호소 이름:</strong> %s <br><strong>보호소 주소:</strong> %s <br><strong>보호소 전화번호:</strong> %s <br> </li>",
				favItem.getPopfile(), favItem.getCareNm(), favItem.getCareAddr(), favItem.getCareTel());
	}

	@RequestMapping("/sendfas")
	public String naverMailSendW(HttpSession session2, String nickname, String email, Model model) {
		String host = "smtp.naver.com";
		String user = "ptiael@naver.com";
		String password = "yesol1101s2";

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

			message.setSubject("보호소 즐겨찾기 목록");

			List<FavoriteShelDTO> favoritesList = services.getLikedShelData(nickname);
			if (favoritesList.isEmpty()) {
				model.addAttribute("msg", "이메일 전송 실패: 즐겨찾기 목록이 비어 있습니다.");
				model.addAttribute("url", "/4jojo/fa/favorites");
				return "alert";
			} else {
				StringBuilder emailContent = new StringBuilder("<h1>보호소 즐겨찾기 목록</h1><br><ul>");
				for (FavoriteShelDTO favItem : favoritesList) {
					emailContent.append(formatFavItemAsHtml(favItem));
				}

				emailContent.append("</ul>");

				message.setContent(emailContent.toString(), "text/html; charset=UTF-8");

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

	public String formatFavItemAsHtml(FavoriteShelDTO favItem) {
		return String.format(
				"<li><br><strong>보호소 이름:</strong> %s <br><strong>보호소 주소:</strong> %s <br><strong>보호소 전화번호:</strong> %s <br> </li>",
				favItem.getCareNm(), favItem.getCareAddr(), favItem.getCareTel());
	}

	@RequestMapping("/sendfaw")
	public String naverMailSend(HttpSession session2, String nickname, String email, Model model) {
		String host = "smtp.naver.com";
		String user = "ptiael@naver.com";
		String password = "yesol1101s2";

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

			message.setSubject("위드펫 즐겨찾기 목록");
			List<FavoriteWithDTO> favoritewList = servicew.getLikedWithData(nickname);
			if (favoritewList.isEmpty()) {
				model.addAttribute("msg", "이메일 전송 실패: 즐겨찾기 목록이 비어 있습니다.");
				model.addAttribute("url", "/4jojo/fa/favoritew");
				return "alert";
			} else {
				StringBuilder emailContent = new StringBuilder("<h1>위드펫 즐겨찾기 목록</h1><br><ul>");
				for (FavoriteWithDTO favItem : favoritewList) {
					emailContent.append(formatFavItemAsHtml(favItem));
				}

				emailContent.append("</ul>");
				message.setContent(emailContent.toString(), "text/html; charset=UTF-8");
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

	public String formatFavItemAsHtml(FavoriteWithDTO favItem) {
		return String.format(
				"<li><br><strong>건물 이름:</strong> %s <br><strong>건물 주소:</strong> %s <br><strong>건물 전화번호:</strong> %s <br> </li>",
				favItem.getBuilding(), favItem.getRoad(), favItem.getTel());
	}
}
