package com.spring.service;

import java.util.List;


import org.apache.ibatis.annotations.Param;

import com.spring.domain.P_DTO;
import com.spring.util.PageMaker;

public interface P_Service {
	List<P_DTO> getP(int pet_notice_no);
	//List<P_DTO> getAllBoard();
	
	//지역별 조회
	List<P_DTO> getAllBoardByPage(PageMaker pageMaker);
	List<P_DTO> getRegionPet(@Param("region") String region, @Param("pageMaker") PageMaker pageMaker);
	int getCountRegionPet(String region);
	int getCountAllBoard();
	
	
	//즐겨찾기
	void registerP(@Param("nickname") String nickname,@Param("dto") P_DTO dto);
	int removeP(int pet_notice_no);
	//List<P_DTO> getRegionPet(String region);
	
	
	int getCountPetNotice(@Param("type") String type
						,@Param("keyword") String keyword
						,@Param("region") String region);
	
	List<P_DTO> getPetNoticeByPage(@Param("type") String type
								, @Param("keyword") String keyword
								, @Param("region") String region
								, @Param("pageMaker") PageMaker pageMaker);
	
}
