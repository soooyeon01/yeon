package com.spring.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Param;

import com.spring.domain.P_DTO;
import com.spring.util.PageMaker;

public interface P_Mapper {
	List<P_DTO> selectP(int pet_notice_no);
	List<P_DTO> selectAllBoard();
	
	
	//지역별 조회
	List<P_DTO> selectAllBoardByPage(PageMaker pageMaker);
	List<P_DTO> selectRegionPet(@Param("region") String region, @Param("pageMaker") PageMaker pageMaker);
	int selectCountAllBoard();
	int selectCountRegionPet(String region);
	//즐겨찾기
	void insertPetnoticeData(@Param("nickname") String nickname,@Param("dto") P_DTO dto);
	int deletePetnoticeData(int pet_notice_no);
	// List<P_DTO> selectRegionPet(String region);
	
	

	//추가
	int CountPetNotice(@Param("type") String type
							,@Param("keyword") String keyword
							,@Param("region") String region);

	List<P_DTO> PetNoticeByPage(@Param("type") String type
									, @Param("keyword") String keyword
									, @Param("region") String region
									, @Param("pageMaker") PageMaker pageMaker);
}
