package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.S_DTO;
import com.spring.util.PageMaker;

public interface S_Mapper {

	List<S_DTO> selectAllBoardByPage(PageMaker pageMaker);

	int selectCountAllBoard();

	List<S_DTO> selectRegionShel(@Param("region") String region, @Param("pageMaker") PageMaker pageMaker);

	int selectCountRegionShel(String region);

	List<S_DTO> selectS(int shelter_no);

	void insertShelterData(@Param("nickname") String nickname, @Param("dto") S_DTO dto);

	int deleteShelterData(int shelter_no);

}
