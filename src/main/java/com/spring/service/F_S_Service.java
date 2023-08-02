package com.spring.service;

import java.util.List;


import com.spring.domain.F_S_DTO;
import com.spring.util.PageMaker;

public interface F_S_Service {
	
	List<F_S_DTO> getSBoard(String nickname);
	List<F_S_DTO> getSBoardByPage(String nickname,PageMaker pageMaker);
	public int getCountAllBoard(String nickname);
	List<F_S_DTO> getLikedPostIdsByUser(String nickname);
}
