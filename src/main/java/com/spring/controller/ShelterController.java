package com.spring.controller;

import java.io.IOException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.spring.domain.F_S_DTO;
import com.spring.domain.P_DTO;
import com.spring.domain.S_DTO;
import com.spring.service.F_S_Service;
import com.spring.service.S_Service;
import com.spring.util.Criteria;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequiredArgsConstructor
@Log4j
@RequestMapping("/shel/*")

public class ShelterController {
	private final S_Service service;
	private final F_S_Service fsservice;
	
	
	@RequestMapping("/sheldetail")
	public ModelAndView getAllBoard(HttpSession session, int shelter_no, Model model, String nickname) {
		Boolean SESS_AUTH=(Boolean) session.getAttribute("SESS_AUTH");
		nickname=(String) session.getAttribute("SESS_NICKNAME");
		List<S_DTO> sheldetailList = service.getS(shelter_no);
		List<F_S_DTO> fasList = fsservice.getLikedPostIdsByUser(nickname);
		if (SESS_AUTH != null && SESS_AUTH) {
			
			 	ModelAndView mav = new ModelAndView("/shel/sheldetail");
		        mav.addObject("sheldetailList", sheldetailList); 
		        mav.addObject("fasList", fasList); 
		        return mav;
		} else {
			ModelAndView mav2 = new ModelAndView("/main/main");
			return mav2;
		}
	}

	// 지역별 조회
	@RequestMapping("/shelall")
	@ResponseBody
	public ModelAndView getShelListByRegion(
			@RequestParam(value = "region", required = false, defaultValue = "") String region,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, Model model,
			Criteria cri) {

		ModelAndView mav = new ModelAndView("/shel/shel");

		PageMaker pageMaker;
		List<S_DTO> shelList;
		int totalCount;
		cri = new Criteria(pageNum);

		if (region.isEmpty()) {
			totalCount = service.getCountAllBoard();
			pageMaker = new PageMaker(cri, totalCount);
			shelList = service.getAllBoardByPage(pageMaker);

		} else {
			totalCount = service.getCountRegionShel(region);
			pageMaker = new PageMaker(cri, totalCount);
			shelList = service.getRegionShel(region, pageMaker);
		}

		Map<String, Object> response = new HashMap<>();
		response.put("shelList", shelList);
		model.addAttribute("pageMaker", pageMaker);
		mav.addObject("response", response);
		return mav;
	}

	// 즐겨찾기

	@RequestMapping("/registershel")
	@ResponseBody
	public String insertF_S(HttpSession session,
			@RequestParam(value = "shelter_no", required = false) String shelter_no,
	        @RequestParam(required = false) String nickname,
	        @RequestParam(required = false) String careNm,
	        @RequestParam(required = false) String divisionNm,
	        @RequestParam(required = false) String saveTrgtAnimal,
	        @RequestParam(required = false) String careAddr,
	        @RequestParam(required = false) String weekOprStime,
	        @RequestParam(required = false) String weekOprEtime,
	        @RequestParam(required = false) String weekCellStime,
	        @RequestParam(required = false) String weekCellEtime,
	        @RequestParam(required = false) String weekendOprStime,
	        @RequestParam(required = false) String weekendOprEtime,
	        @RequestParam(required = false) String weekendCellStime,
	        @RequestParam(required = false) String weekendCellEtime,
	        @RequestParam(required = false) String closeDay,
	        @RequestParam(required = false) String careTel
	) throws ServletException, IOException {
		F_S_DTO dto = new F_S_DTO();
		S_DTO dto2 = new S_DTO();
		Date favorites_reg_date = new Date(System.currentTimeMillis());
		if (shelter_no == null || shelter_no.equals("")) {
			shelter_no = "-1";
		}
			nickname=(String) session.getAttribute("SESS_NICKNAME");
		 	dto.setNickname(nickname);
		    dto.setFavorites_reg_date(favorites_reg_date);
		    dto2.setShelter_no(Integer.parseInt(shelter_no));
		    dto2.setCareNm(careNm);
		    dto2.setDivisionNm(divisionNm);
		    dto2.setSaveTrgtAnimal(saveTrgtAnimal);
		    dto2.setCareAddr(careAddr);
		    dto2.setWeekOprStime(weekOprStime);
		    dto2.setWeekOprEtime(weekOprEtime);
		    dto2.setWeekCellStime(weekCellStime);
		    dto2.setWeekCellEtime(weekCellEtime);
		    dto2.setWeekendOprStime(weekendOprStime);
		    dto2.setWeekendOprEtime(weekendOprEtime);
		    dto2.setWeekendCellStime(weekendCellStime);
		    dto2.setWeekendCellEtime(weekendCellEtime);
		    dto2.setCloseDay(closeDay);
		    dto2.setCareTel(careTel);

		service.registerShelterData(nickname, dto2);

		JsonObject jsonObj = new JsonObject();
	    jsonObj.addProperty("success", true);

	    return jsonObj.toString();
	}

	@RequestMapping("/removeshel")
	@ResponseBody
	public String deleteF_S(@RequestParam(value = "shelter_no", required = false) String shelter_no,
	        @RequestParam(required = false) String nickname,
	        @RequestParam(required = false) String careNm,
	        @RequestParam(required = false) String divisionNm,
	        @RequestParam(required = false) String saveTrgtAnimal,
	        @RequestParam(required = false) String careAddr,
	        @RequestParam(required = false) String weekOprStime,
	        @RequestParam(required = false) String weekOprEtime,
	        @RequestParam(required = false) String weekCellStime,
	        @RequestParam(required = false) String weekCellEtime,
	        @RequestParam(required = false) String weekendOprStime,
	        @RequestParam(required = false) String weekendOprEtime,
	        @RequestParam(required = false) String weekendCellStime,
	        @RequestParam(required = false) String weekendCellEtime,
	        @RequestParam(required = false) String closeDay,
	        @RequestParam(required = false) String careTel

	) throws ServletException, IOException {
		F_S_DTO dto = new F_S_DTO();
		S_DTO dto2 = new S_DTO();
		Date favorites_reg_date = new Date(System.currentTimeMillis());
		if (shelter_no == null || shelter_no.equals("")) {
			shelter_no = "-1";
		}

		dto.setNickname(nickname);
	    dto.setFavorites_reg_date(favorites_reg_date);
	    dto2.setShelter_no(Integer.parseInt(shelter_no));
	    dto2.setCareNm(careNm);
	    dto2.setDivisionNm(divisionNm);
	    dto2.setSaveTrgtAnimal(saveTrgtAnimal);
	    dto2.setCareAddr(careAddr);
	    dto2.setWeekOprStime(weekOprStime);
	    dto2.setWeekOprEtime(weekOprEtime);
	    dto2.setWeekCellStime(weekCellStime);
	    dto2.setWeekCellEtime(weekCellEtime);
	    dto2.setWeekendOprStime(weekendOprStime);
	    dto2.setWeekendOprEtime(weekendOprEtime);
	    dto2.setWeekendCellStime(weekendCellStime);
	    dto2.setWeekendCellEtime(weekendCellEtime);
	    dto2.setCloseDay(closeDay);
	    dto2.setCareTel(careTel);

		int result = service.removeShelterData(Integer.parseInt(shelter_no));

		JsonObject jsonObj = new JsonObject();
	    jsonObj.addProperty("result", result);

	    return jsonObj.toString();
	}
}
