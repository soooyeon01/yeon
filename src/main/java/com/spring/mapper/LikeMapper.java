package com.spring.mapper;

import com.spring.domain.LikeDTO;

public interface LikeMapper {
	int likeUp(LikeDTO dto);
	int likeDown(LikeDTO dto);
	int findLike(LikeDTO dto);
	int getLikeCnt(LikeDTO dto);
}
