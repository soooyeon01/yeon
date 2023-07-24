package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.F_W_DTO;
import com.spring.util.PageMaker;

public interface F_W_Mapper {
	List<F_W_DTO> selectWBoard();
	int selectCountWBoard();	
	List<F_W_DTO> selectWBoardByPage(@Param("nickname")String nickname,@Param("pageMaker")PageMaker pageMaker);
}
