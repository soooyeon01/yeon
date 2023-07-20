package com.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.domain.ReplyDTO;
import com.spring.service.LoginService;
import com.spring.service.NoticeService;
import com.spring.service.ReplyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reply/*")
@RequiredArgsConstructor
public class ReplyController {
	
	
}
