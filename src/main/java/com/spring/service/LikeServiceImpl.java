package com.spring.service;

import org.springframework.stereotype.Service;

import com.spring.domain.LikeDTO;
import com.spring.mapper.CommunityMapper;
import com.spring.mapper.LikeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
	private final LikeMapper mapper;
	
	@Override
	public int likeUp(LikeDTO dto) {
		// TODO Auto-generated method stub
		return mapper.likeUp(dto);
	}

	@Override
	public int likeDown(LikeDTO dto) {
		// TODO Auto-generated method stub
		return mapper.likeDown(dto);
	}

	@Override
	public int findLike(LikeDTO dto) {
		// TODO Auto-generated method stub
		return mapper.findLike(dto);
	}

	@Override
	public int getLikeCnt(LikeDTO dto) {
		// TODO Auto-generated method stub
		return mapper.getLikeCnt(dto);
	}
	
	
}
