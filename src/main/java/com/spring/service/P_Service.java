package com.spring.service;

import java.util.List;

import com.spring.domain.P_DTO;
import com.spring.domain.S_DTO;
import com.spring.util.PageMaker;

public interface P_Service {
	List<P_DTO> getP(int pet_notice_no);
	List<P_DTO> getAllBoard();
	int getCountAllBoard();
	List<P_DTO> getAllBoardByPage(PageMaker pageMaker);
	
	
}
