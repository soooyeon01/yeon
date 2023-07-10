package com.spring.controller;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spring.service.TimeService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/time/*")
@RequiredArgsConstructor
@Log4j
public class TimeController {
	@NonNull
	private TimeService service;
	
	@GetMapping("/get2") //localhost:8080/ex00/time/get2
	private String getTime2(Model model) {
		log.info("getTime2()");
		model.addAttribute("time", service.getTime2());
		return "time";
	}
}


