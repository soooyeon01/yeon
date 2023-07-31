package com.spring.service;

import java.util.List;

import com.spring.domain.F_P_DTO;
import com.spring.domain.F_S_DTO;
import com.spring.domain.P_DTO;
import com.spring.util.PageMaker;

public interface F_P_Service {
	
	
	
	List<F_P_DTO> getPBoard(String nickname);
	List<F_P_DTO> getPBoardByPage(String nickname,PageMaker pageMaker);
	public int getCountAllBoard(String nickname);
	List<F_P_DTO> getLikedPostIdsByUser(String nickname);
}
