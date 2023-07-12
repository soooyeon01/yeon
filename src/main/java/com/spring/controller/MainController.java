package com.spring.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.service.MypageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/main/*")
@RequiredArgsConstructor
public class MainController {

	// localhost:8080/4jojo/main/main
		@GetMapping("main")
		public String mainPage() {
			
			return "/main/main";
		}
}
