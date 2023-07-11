package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.mapper.BoardMapper;
import com.spring.util.Criteria;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
@Log4j
@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {
	private final BoardMapper mapper;
	// localhost:8080/ex01/board/board-all?pageNum=3
	@GetMapping("/board-all")
	public String boardAll(Model model, Criteria cri ) {
		log.info(cri.getPageNum());
		 //new ArrayList<BoardDTO>();	
		PageMaker pageMaker = new PageMaker(cri, 101);
		model.addAttribute("boardList",mapper.selectAllBoardByPage(pageMaker));
		model.addAttribute("pageMaker",pageMaker);//바인딩
		return "/board/board";
	}
}
