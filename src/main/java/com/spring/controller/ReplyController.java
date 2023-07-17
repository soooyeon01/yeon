package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.service.LoginService;
import com.spring.service.NoticeService;
import com.spring.service.ReplyService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/reply/*")
@RequiredArgsConstructor
public class ReplyController {
	private ReplyService service;
	
}
