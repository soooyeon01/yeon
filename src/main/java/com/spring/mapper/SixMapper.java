package com.spring.mapper;

import java.util.List;

import com.spring.domain.W_DTO;

public interface SixMapper {
	List<W_DTO> selectRC(String category3);
	List<W_DTO> selectAC(String category3);
	List<W_DTO> selectCU(String category3);
	List<W_DTO> selectHP(String category3);
	List<W_DTO> selectGO(String category3);
	List<W_DTO> selectCA(String category3);
}
