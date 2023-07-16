package com.spring.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
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
	
	@GetMapping("/petall")
	public String getPBoard(Model model, Criteria cri) {
		int totalCount = service.getCountAllBoard();
	    PageMaker pageMaker = new PageMaker(cri, totalCount);
		model.addAttribute("petList",service.getAllBoardByPage(pageMaker));
		model.addAttribute("pageMaker",pageMaker);
		return "/pet/pet";
	}
	@GetMapping("/petdetail")
	public String getAllBoard(int pet_notice_no, Model model) {
		
		model.addAttribute("petdetailList",service.getP(pet_notice_no));
		
		return "/pet/petdetail";
	}
	   
	@RequestMapping(value = "/petselect", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, List<P_DTO>> getPetListByRegion(@RequestParam("region") String region) {
        log.info("이게 널이니?"+region);
        List<P_DTO> petList = service.getRegionPet(region);

        Map<String, List<P_DTO>> response = new HashMap<>();
        response.put("petList", petList);

        return response;
    }
	
	
	
	
	
	@RequestMapping("/registerpet")
	@ResponseBody
	protected void  insertF_P(HttpServletRequest request, HttpServletResponse response
	  
	    
	)throws ServletException, IOException {
	    F_P_DTO dto = new F_P_DTO();
	    P_DTO dto2 = new P_DTO();
	   
		String pet_notice_no = request.getParameter("pet_notice_no");
		
		Date favoritep_reg_date = new Date(System.currentTimeMillis());
        if (pet_notice_no == null || pet_notice_no.equals("")) {
        	pet_notice_no = "-1";
        }
        
        dto.setNickname(request.getParameter("nickname"));
        dto.setFavoritep_reg_date(favoritep_reg_date);
        dto2.setPet_notice_no(Integer.parseInt(pet_notice_no));
        dto2.setCareNm( request.getParameter("careNm"));
        dto2.setHappenDt( request.getParameter("happendt"));
        dto2.setHappenPlace( request.getParameter("happenPlace"));
        dto2.setCareAddr( request.getParameter("careAddr"));
        dto2.setKindCd( request.getParameter("kindCd"));
        dto2.setColorCd( request.getParameter("colorCd"));
        dto2.setAge( request.getParameter("age"));
        dto2.setWeight( request.getParameter("weight"));
        dto2.setNoticeNo( request.getParameter("noticeNo"));
        dto2.setNoticeSdt( request.getParameter("noticeSdt"));
        dto2.setNoticeEdt( request.getParameter("noticeEdt"));
        dto2.setPopfile( request.getParameter("popfile"));
        dto2.setProcessState( request.getParameter("processState"));
        dto2.setSexCd( request.getParameter("sexCd"));
        dto2.setNeuterYn( request.getParameter("neuterYn"));
        dto2.setSpecialMark( request.getParameter("specialMark"));
		
        int result= service.registerP(dto2);
		
		
		JsonObject jsonObj = new JsonObject();
		jsonObj.addProperty("result", result);
	
		response.getWriter().println( new Gson().toJson(jsonObj) );
	}
	@RequestMapping("/removepet")
	@ResponseBody
	protected void  deleteF_P(HttpServletRequest request, HttpServletResponse response
	  
	    
	)throws ServletException, IOException {
		 F_P_DTO dto = new F_P_DTO();
		    P_DTO dto2 = new P_DTO();
		   
			String pet_notice_no = request.getParameter("pet_notice_no");
			
			Date favoritep_reg_date = new Date(System.currentTimeMillis());
	        if (pet_notice_no == null || pet_notice_no.equals("")) {
	        	pet_notice_no = "-1";
	        }
	        
	        dto.setNickname(request.getParameter("nickname"));
	        dto.setFavoritep_reg_date(favoritep_reg_date);
	        dto2.setPet_notice_no(Integer.parseInt(pet_notice_no));
	        dto2.setCareNm( request.getParameter("careNm"));
	        dto2.setHappenDt( request.getParameter("happendt"));
	        dto2.setHappenPlace( request.getParameter("happenPlace"));
	        dto2.setCareAddr( request.getParameter("careAddr"));
	        dto2.setKindCd( request.getParameter("kindCd"));
	        dto2.setColorCd( request.getParameter("colorCd"));
	        dto2.setAge( request.getParameter("age"));
	        dto2.setWeight( request.getParameter("weight"));
	        dto2.setNoticeNo( request.getParameter("noticeNo"));
	        dto2.setNoticeSdt( request.getParameter("noticeSdt"));
	        dto2.setNoticeEdt( request.getParameter("noticeEdt"));
	        dto2.setPopfile( request.getParameter("popfile"));
	        dto2.setProcessState( request.getParameter("processState"));
	        dto2.setSexCd( request.getParameter("sexCd"));
	        dto2.setNeuterYn( request.getParameter("neuterYn"));
	        dto2.setSpecialMark( request.getParameter("specialMark"));
			
	        int result= service.removeP(Integer.parseInt(pet_notice_no));
			
			
			JsonObject jsonObj = new JsonObject();
			jsonObj.addProperty("result", result);
		
			response.getWriter().println( new Gson().toJson(jsonObj) );
		}
}
