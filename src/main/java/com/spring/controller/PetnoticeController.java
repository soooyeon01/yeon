package com.spring.controller;

import java.io.IOException;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.spring.domain.F_P_DTO;
import com.spring.domain.P_DTO;

import com.spring.service.P_Service;
import com.spring.util.Criteria;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/pet/*")

public class PetnoticeController {

	private final P_Service service;
	// localhost:8080/4jojo/pet/petdetail

	@RequestMapping("/petall")
	@ResponseBody
	public ModelAndView getPetListByRegion(
			@RequestParam(value = "region", required = false, defaultValue = "") String region,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "keyword", required = false) String keyword,
			Model model,
			Criteria cri) {

		ModelAndView mav = new ModelAndView("/pet/pet");

		PageMaker pageMaker;
		List<P_DTO> petList;
		int totalCount;
		cri = new Criteria(pageNum);		
		Map<String, Object> response = new HashMap<>();
		
		//추가
		totalCount = service.getCountPetNotice(type,keyword,region);
		pageMaker = new PageMaker(cri, totalCount);
		petList = service.getPetNoticeByPage(type,keyword,region,pageMaker);	

		
		response.put("petList", petList);
		model.addAttribute("pageMaker", pageMaker);
		mav.addObject("response", response);
		return mav;
	}

	// 상세조회
	@GetMapping("/petdetail")
	public String getAllBoard(HttpSession session, int pet_notice_no, Model model) {
		Boolean SESS_AUTH=(Boolean) session.getAttribute("SESS_AUTH");
		String email = (String) session.getAttribute("SESS_EMAIL");
		if (SESS_AUTH != null && SESS_AUTH) {
			model.addAttribute("petdetailList", service.getP(pet_notice_no));

			return "/pet/petdetail";
		} else {
			return "main/main";
		}
	}

	// 즐겨찾기 등록
	@PostMapping("/registerpet")
	@ResponseBody
	public String insertF_P(HttpSession session,
	        @RequestParam(value = "pet_notice_no", required = false) String pet_notice_no,
	        @RequestParam(required = false) String nickname, @RequestParam(required = false) String careNm,
	        @RequestParam(required = false) String happendt, @RequestParam(required = false) String happenPlace,
	        @RequestParam(required = false) String careAddr, @RequestParam(required = false) String kindCd,
	        @RequestParam(required = false) String colorCd, @RequestParam(required = false) String age,
	        @RequestParam(required = false) String weight, @RequestParam(required = false) String noticeNo,
	        @RequestParam(required = false) String noticeSdt, @RequestParam(required = false) String noticeEdt,
	        @RequestParam(required = false) String popfile, @RequestParam(required = false) String processState,
	        @RequestParam(required = false) String sexCd, @RequestParam(required = false) String neuterYn,
	        @RequestParam(required = false) String specialMark
	) throws ServletException, IOException {
	    F_P_DTO dto = new F_P_DTO();
	    P_DTO dto2 = new P_DTO();
	    
	    log.info(pet_notice_no);
	    Date favoritep_reg_date = new Date(System.currentTimeMillis());
	    if (pet_notice_no == null || pet_notice_no.equals("")) {
	        pet_notice_no = "-1";
	    }
	    nickname=(String) session.getAttribute("SESS_NICKNAME");
	    dto.setNickname(nickname);
	    dto.setFavoritep_reg_date(favoritep_reg_date);
	    dto2.setPet_notice_no(Integer.parseInt(pet_notice_no));
	    dto2.setCareNm(careNm);
	    dto2.setHappenDt(happendt);
	    dto2.setHappenPlace(happenPlace);
	    dto2.setCareAddr(careAddr);
	    dto2.setKindCd(kindCd);
	    dto2.setColorCd(colorCd);
	    dto2.setAge(age);
	    dto2.setWeight(weight);
	    dto2.setNoticeNo(noticeNo);
	    dto2.setNoticeSdt(noticeSdt);
	    dto2.setNoticeEdt(noticeEdt);
	    dto2.setPopfile(popfile);
	    dto2.setProcessState(processState);
	    dto2.setSexCd(sexCd);
	    dto2.setNeuterYn(neuterYn);
	    dto2.setSpecialMark(specialMark);

	    service.registerP(nickname,dto2);

	    JsonObject jsonObj = new JsonObject();
	    jsonObj.addProperty("success", true);

	    return jsonObj.toString();
	}

	// 즐겨찾기 삭제
	@RequestMapping("/removepet")
	@ResponseBody
	public String deleteF_P(@RequestParam(value = "pet_notice_no", required = false) String pet_notice_no,
	        @RequestParam(required = false) String nickname, @RequestParam(required = false) String careNm,
	        @RequestParam(required = false) String happendt, @RequestParam(required = false) String happenPlace,
	        @RequestParam(required = false) String careAddr, @RequestParam(required = false) String kindCd,
	        @RequestParam(required = false) String colorCd, @RequestParam(required = false) String age,
	        @RequestParam(required = false) String weight, @RequestParam(required = false) String noticeNo,
	        @RequestParam(required = false) String noticeSdt, @RequestParam(required = false) String noticeEdt,
	        @RequestParam(required = false) String popfile, @RequestParam(required = false) String processState,
	        @RequestParam(required = false) String sexCd, @RequestParam(required = false) String neuterYn,
	        @RequestParam(required = false) String specialMark

	) throws ServletException, IOException {
		F_P_DTO dto = new F_P_DTO();
	    P_DTO dto2 = new P_DTO();

	    log.info(pet_notice_no);
	    Date favoritep_reg_date = new Date(System.currentTimeMillis());
	    if (pet_notice_no == null || pet_notice_no.equals("")) {
	        pet_notice_no = "-1";
	    }

	    dto.setNickname(nickname);
	    dto.setFavoritep_reg_date(favoritep_reg_date);
	    dto2.setPet_notice_no(Integer.parseInt(pet_notice_no));
	    dto2.setCareNm(careNm);
	    dto2.setHappenDt(happendt);
	    dto2.setHappenPlace(happenPlace);
	    dto2.setCareAddr(careAddr);
	    dto2.setKindCd(kindCd);
	    dto2.setColorCd(colorCd);
	    dto2.setAge(age);
	    dto2.setWeight(weight);
	    dto2.setNoticeNo(noticeNo);
	    dto2.setNoticeSdt(noticeSdt);
	    dto2.setNoticeEdt(noticeEdt);
	    dto2.setPopfile(popfile);
	    dto2.setProcessState(processState);
	    dto2.setSexCd(sexCd);
	    dto2.setNeuterYn(neuterYn);
	    dto2.setSpecialMark(specialMark);

		int result = service.removeP(Integer.parseInt(pet_notice_no));

		JsonObject jsonObj = new JsonObject();
	    jsonObj.addProperty("result", result);

	    return jsonObj.toString();
	}
}
