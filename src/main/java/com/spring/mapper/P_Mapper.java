package com.spring.mapper;

import java.util.List;

import com.spring.domain.P_DTO;
import com.spring.util.PageMaker;

public interface P_Mapper {
	List<P_DTO> selectP(int pet_notice_no);
	List<P_DTO> selectAllBoard();
	int selectCountAllBoard();
	List<P_DTO> selectAllBoardByPage(PageMaker pageMaker);
}
