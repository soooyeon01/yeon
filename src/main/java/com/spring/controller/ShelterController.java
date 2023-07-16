package com.spring.controller;

import java.io.IOException;

import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.spring.domain.F_S_DTO;
import com.spring.domain.S_DTO;

import com.spring.service.S_Service;
import com.spring.util.Criteria;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor

@RequestMapping("/shel/*")
public class ShelterController {
	private final S_Service service;
	@GetMapping("/sheldetail")
	public String getS(int shelter_no, Model model) {
		
		model.addAttribute("sheldetailList",service.getS(shelter_no));
		return "/shel/sheldetail";
	}
	@GetMapping("/shelall")
	public String getAllBoard(Criteria cri, Model model) {
		int totalCount = service.getCountAllBoard();
		PageMaker pageMaker = new PageMaker(cri, totalCount); 
		model.addAttribute("shelList",service.getAllBoardByPage(pageMaker));
		model.addAttribute("pageMaker",pageMaker);
		return "/shel/shel";
	}
	
	@RequestMapping("/shelselect")
	public String SelectRegionPet(S_DTO dto) {
		service.getRegionShel(dto);
		return "/shel/shel";
	}
	
	
	@RequestMapping("/registershel")
	@ResponseBody
	protected void  insertF_S(HttpServletRequest request, HttpServletResponse response
	  
	    
	)throws ServletException, IOException {
	    F_S_DTO dto = new F_S_DTO();
	    S_DTO dto2 = new S_DTO();
	   
		String shelter_no = request.getParameter("shelter_no");
		
		Date favorites_reg_date = new Date(System.currentTimeMillis());
        if (shelter_no == null || shelter_no.equals("")) {
            shelter_no = "-1";
        }
        
        dto.setNickname(request.getParameter("nickname"));
        dto.setFavorites_reg_date(favorites_reg_date);
        dto2.setShelter_no(Integer.parseInt(shelter_no));
        dto2.setCareNm( request.getParameter("careNm"));
        dto2.setDivisionNm( request.getParameter("divisionNm"));
        dto2.setSaveTrgtAnimal( request.getParameter("saveTrgtAnimal"));
        dto2.setCareAddr( request.getParameter("careAddr"));
        dto2.setWeekOprStime( request.getParameter("weekOprStime"));
        dto2.setWeekOprEtime( request.getParameter("weekOprEtime"));
        dto2.setWeekCellStime( request.getParameter("weekCellStime"));
        dto2.setWeekCellEtime( request.getParameter("weekCellEtime"));
        dto2.setWeekendOprStime( request.getParameter("weekendOprStime"));
        dto2.setWeekendOprEtime( request.getParameter("weekendOprEtime"));
        dto2.setWeekendCellStime( request.getParameter("weekendCellStime"));
        dto2.setWeekendCellEtime( request.getParameter("weekendCellEtime"));
        dto2.setCloseDay( request.getParameter("closeDay"));
        dto2.setCareTel( request.getParameter("careTel"));
		
		int result= service.registerShelterData(dto2);
		
		
		JsonObject jsonObj = new JsonObject();
		jsonObj.addProperty("result", result);
	
		response.getWriter().println( new Gson().toJson(jsonObj) );
	}
	@RequestMapping("/removeshel")
	@ResponseBody
	protected void  deleteF_S(HttpServletRequest request, HttpServletResponse response
	  
	    
	)throws ServletException, IOException {
	    F_S_DTO dto = new F_S_DTO();
	    S_DTO dto2 = new S_DTO();
	   
		String shelter_no = request.getParameter("shelter_no");
		
		Date favorites_reg_date = new Date(System.currentTimeMillis());
        if (shelter_no == null || shelter_no.equals("")) {
            shelter_no = "-1";
        }
        
        dto.setNickname(request.getParameter("nickname"));
        dto.setFavorites_reg_date(favorites_reg_date);
        dto2.setShelter_no(Integer.parseInt(shelter_no));
        dto2.setCareNm( request.getParameter("careNm"));
        dto2.setDivisionNm( request.getParameter("divisionNm"));
        dto2.setSaveTrgtAnimal( request.getParameter("saveTrgtAnimal"));
        dto2.setCareAddr( request.getParameter("careAddr"));
        dto2.setWeekOprStime( request.getParameter("weekOprStime"));
        dto2.setWeekOprEtime( request.getParameter("weekOprEtime"));
        dto2.setWeekCellStime( request.getParameter("weekCellStime"));
        dto2.setWeekCellEtime( request.getParameter("weekCellEtime"));
        dto2.setWeekendOprStime( request.getParameter("weekendOprStime"));
        dto2.setWeekendOprEtime( request.getParameter("weekendOprEtime"));
        dto2.setWeekendCellStime( request.getParameter("weekendCellStime"));
        dto2.setWeekendCellEtime( request.getParameter("weekendCellEtime"));
        dto2.setCloseDay( request.getParameter("closeDay"));
        dto2.setCareTel( request.getParameter("careTel"));
		
		int result= service.removeShelterData(Integer.parseInt(shelter_no));
		
		
		JsonObject jsonObj = new JsonObject();
		jsonObj.addProperty("result", result);
	
		response.getWriter().println( new Gson().toJson(jsonObj) );
	}
}
