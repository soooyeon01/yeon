package com.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/logout/*")
@RequiredArgsConstructor
public class LogoutController {
	
	@RequestMapping("")
    public String logout(HttpSession session) {
        session.invalidate();
 
        return "/logout/logout";
    }


}
