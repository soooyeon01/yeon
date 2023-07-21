package com.spring.controller;

import java.io.IOException;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.spring.domain.F_S_DTO;
import com.spring.domain.S_DTO;
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
	@GetMapping("/sheldetail")
	public String getS(int shelter_no, Model model) {
		
		model.addAttribute("sheldetailList",service.getS(shelter_no));
		return "/shel/sheldetail";
	}
	
	// 지역별 조회
	@RequestMapping("/shelall")
	@ResponseBody
    public ModelAndView getShelListByRegion(
    		@RequestParam(value="region", required=false, defaultValue="") String region,
			@RequestParam(value="pageNum", required=false, defaultValue="1") int pageNum,
			Model model, 
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


	
	
	//즐겨찾기
	
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
