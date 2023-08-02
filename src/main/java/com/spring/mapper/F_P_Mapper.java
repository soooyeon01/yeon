package com.spring.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Param;

import com.spring.domain.F_P_DTO;
import com.spring.util.PageMaker;

public interface F_P_Mapper {
	

	List<F_P_DTO> selectLikedPostIdsByUser(String nickname);
	
	List<F_P_DTO> selectPBoard(String nickname);
	
	int selectCountPBoard(String nickname);
	
	List<F_P_DTO> selectPBoardByPage(@Param("nickname")String nickname,@Param("pageMaker")PageMaker pageMaker);
	
	
}
