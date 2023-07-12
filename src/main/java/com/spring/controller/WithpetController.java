package com.spring.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.domain.F_W_DTO;
import com.spring.domain.W_DTO;
import com.spring.service.W_Service;
import com.spring.util.Criteria;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/with/*")
public class WithpetController {
	private final W_Service service;
	
	@GetMapping("/withall")
	public String getAllBoard(Criteria cri, Model model) {
		
		PageMaker pageMaker = new PageMaker(cri, 101); 
		model.addAttribute("withList",service.getAllBoardByPage(pageMaker));
		model.addAttribute("pageMaker",pageMaker);
		return "/with/with";
	}
	@GetMapping("/withdetail")
	public String getW(int with_pet_no, Model model) {
		model.addAttribute("withdetailList",service.getW(with_pet_no));
		return "/with/withdetail";
	}
	@PostMapping("/registerwith")
	public String insertF_W(
							@RequestParam("with_pet_no") String with_pet_no,
							@RequestParam("building") String building,
							@RequestParam("category3") String category3,
							@RequestParam("road") String road,
							@RequestParam("tel") String tel,
							@RequestParam("homepage") String homepage,
							@RequestParam("hour") String hour,
							@RequestParam("parking")String parking,
							@RequestParam("with_pet_info") String with_pet_info,
							@RequestParam("only_pet_info") String only_pet_info,
							@RequestParam("pet_size") String pet_size,
							@RequestParam("pet_limit") String pet_limit,
							@RequestParam("inside") String inside,
							@RequestParam("outside") String outside,
							@RequestParam("extra") String extra
							) {
		F_W_DTO dto = new F_W_DTO();
		W_DTO dto2 = new W_DTO();
		if (with_pet_no == null || with_pet_no.equals("")) {
        	with_pet_no = "-1";
        }
		Date favoritew_reg_date = new Date();
	
		dto.setFavoritew_reg_date(favoritew_reg_date);
		dto2.setWith_pet_no(Integer.parseInt(with_pet_no));
		dto2.setBuilding(building);
		dto2.setCategory3(category3);
		dto2.setRoad(road);
		dto2.setTel(tel);
		dto2.setHomepage(homepage);
		dto2.setHour(hour);
		dto2.setParking(parking);
		dto2.setWith_pet_info(with_pet_info);
		dto2.setOnly_pet_info(only_pet_info);
		dto2.setPet_size(pet_size);
		dto2.setPet_limit(pet_limit);
		dto2.setInside(inside);
		dto2.setOutside(outside);
		dto2.setExtra(extra);
		
		return "/with/withdetail";
	}
	
}
