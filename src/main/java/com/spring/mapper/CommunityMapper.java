package com.spring.mapper;

import java.util.List;

import com.spring.util.PageMaker;
import com.spring.domain.CommunityDTO;

public interface CommunityMapper {
	CommunityDTO selectCommunity(int c_no);
	List<CommunityDTO> selectAllCommunity();
	List<CommunityDTO> selectMyCommunity(String nickname);
	List<CommunityDTO> selectAllCommunityByPage(PageMaker pageMaker);
	List<CommunityDTO> viewCntCommunity();
	List<CommunityDTO> likeCntCommunity();
	int replyCntCommunity(int c_no);
	int insertCommunity(CommunityDTO dto);
	int updateCommunity(CommunityDTO dto);
	int deleteCommunity(int c_no);
	int viewCnt(int c_no);

}
