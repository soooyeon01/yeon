package com.spring.service;

import java.util.List;

import com.spring.domain.F_W_DTO;
import com.spring.util.PageMaker;

public interface F_W_Service {
	List<F_W_DTO> getWBoard();
	List<F_W_DTO> getWBoardByPage(String nickname,PageMaker pageMaker);
	public int getCountAllBoard();
}
