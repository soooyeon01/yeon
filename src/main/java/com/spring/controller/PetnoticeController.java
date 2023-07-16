package com.spring.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.spring.domain.F_P_DTO;
import com.spring.domain.P_DTO;

import com.spring.service.P_Service;
import com.spring.util.Criteria;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;

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
	@PostMapping("/petselect")
    public @ResponseBody List<P_DTO> getAnimalsByRegion(@RequestParam("region") String region) {
        // 지역에 따라 동물 목록을 검색하는 로직 구현
        // 예시 코드 (실제 구현 시 수정 필요)

        // 1. DB에서 동물 목록을 검색하거나 서비스 클래스의 메소드를 호출하여 데이터를 가져옵니다.
        //    아래는 임시 데이터 목록을 생성하는 코드입니다.
        List<P_DTO> animals = new ArrayList<>();
        if ("서울특별시".equalsIgnoreCase(region)) {
            service.getRegionPet(region);
        } else if ("부산광역시".equalsIgnoreCase(region)) {
        	service.getRegionPet(region);
        }

        // 2. 동물 목록을 프론트엔드로 반환합니다.
        return animals;
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
